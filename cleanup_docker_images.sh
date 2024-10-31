#!/bin/bash

# Set needed variables
REPO_OWNER=$(echo "$1" | tr '[:upper:]' '[:lower:]')
REPO_NAME="$2"
TOKEN="$3"
KEEP="$4"

# Get all package versions (tags) along with their creation dates from the GitHub Container Registry
VERSIONS=$(curl -H "Authorization: Bearer $TOKEN" \
  -H "Accept: application/vnd.github.v3+json" \
  https://api.github.com/orgs/"$REPO_OWNER"/packages/container/"$REPO_NAME"/versions)

# Extract the tag names and created_at timestamps
TAGS_WITH_DATES=$(echo "$VERSIONS" | jq -r '.[] | "\(.name) \(.created_at)"')

echo -e "Found tags with timestamps:\n$TAGS_WITH_DATES"

# Sort the tags by creation date (newest first) and keep the latest $KEEP tags
TAGS_TO_DELETE=$(echo "$TAGS_WITH_DATES" | sort -k2,2r | tail -n +$((KEEP + 1)) | awk '{print $1}')

echo -e "Tags to delete:\n$TAGS_TO_DELETE"

# Delete the older tags
for TAG in $TAGS_TO_DELETE; do
  echo "Deleting $TAG"

  # Get the version ID of the tag
  VERSION_ID=$(echo "$VERSIONS" | jq -r ".[] | select(.name==\"$TAG\") | .id")

  if [ -n "$VERSION_ID" ]; then
    # Delete the version using its ID
    curl -X DELETE \
      -H "Authorization: Bearer $TOKEN" \
      -H "Accept: application/vnd.github.v3+json" \
      https://api.github.com/orgs/"$REPO_OWNER"/packages/container/"$REPO_NAME"/versions/"$VERSION_ID"
    echo "Deleted $TAG (ID: $VERSION_ID)"
  else
    echo "Failed to get version ID for $TAG"
  fi
done