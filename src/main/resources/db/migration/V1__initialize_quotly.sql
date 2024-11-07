CREATE SEQUENCE IF NOT EXISTS quote_comments_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS quote_comments
(
    comment_id BIGINT   DEFAULT NEXTVAL(quote_comments_sequence) NOT NULL,
    parent     BIGINT                 NULL,
    user_id    BIGINT   DEFAULT 0     NOT NULL,
    quote_id   BIGINT   DEFAULT 0     NOT NULL,
    comment    VARCHAR(1024)          NULL,
    created_at datetime DEFAULT NOW() NULL,
    updated_at datetime               NULL,
    deleted_at datetime               NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (comment_id)
);

CREATE TABLE IF NOT EXISTS quote_reactions
(
    user_id       BIGINT DEFAULT 0 NOT NULL,
    quote_id      BIGINT DEFAULT 0 NOT NULL,
    reaction_name VARCHAR(128)     NULL,
    deleted_at    datetime         NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (user_id, quote_id)
);

CREATE SEQUENCE IF NOT EXISTS quotes_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS quotes
(
    quote_id   BIGINT   DEFAULT NEXTVAL(quotes_sequence) NOT NULL,
    user_id    BIGINT   DEFAULT 0     NOT NULL,
    quote      VARCHAR(1024)          NULL,
    created_at datetime DEFAULT NOW() NULL,
    changed_at datetime               NULL,
    deleted_at datetime               NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (quote_id)
);

CREATE SEQUENCE IF NOT EXISTS roles_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS roles
(
    role_id    BIGINT   DEFAULT NEXTVAL(roles_sequence) NOT NULL,
    name       VARCHAR(64)            NULL,
    created_at datetime DEFAULT NOW() NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (role_id)
);

CREATE TABLE IF NOT EXISTS saved_quotes
(
    user_id    BIGINT   DEFAULT 0     NOT NULL,
    quote_id   BIGINT   DEFAULT 0     NOT NULL,
    created_at datetime DEFAULT NOW() NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (user_id, quote_id)
);

CREATE TABLE IF NOT EXISTS user_roles
(
    user_id BIGINT DEFAULT 0 NOT NULL,
    role_id BIGINT DEFAULT 0 NOT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (user_id, role_id)
);

CREATE SEQUENCE IF NOT EXISTS users_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS users
(
    user_id       BIGINT   DEFAULT NEXTVAL(users_sequence) NOT NULL,
    discord_id    VARCHAR(64)            NULL,
    email_address VARCHAR(512)           NULL,
    display_name  VARCHAR(64)            NULL,
    created_at    datetime DEFAULT NOW() NULL,
    deleted_at    datetime               NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (user_id)
);

ALTER TABLE quote_comments
    ADD CONSTRAINT fk_quote_comments_parent FOREIGN KEY (parent) REFERENCES quote_comments (comment_id) ON DELETE CASCADE;

CREATE INDEX fk_quote_comments_parent ON quote_comments (parent);

ALTER TABLE quote_comments
    ADD CONSTRAINT fk_quote_comments_quote_id FOREIGN KEY (quote_id) REFERENCES quotes (quote_id) ON DELETE CASCADE;

CREATE INDEX fk_quote_comments_quote_id ON quote_comments (quote_id);

ALTER TABLE quote_comments
    ADD CONSTRAINT fk_quote_comments_user_id FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE;

CREATE INDEX fk_quote_comments_user_id ON quote_comments (user_id);

ALTER TABLE quote_reactions
    ADD CONSTRAINT fk_quote_reactions_quote_id FOREIGN KEY (quote_id) REFERENCES quotes (quote_id) ON DELETE CASCADE;

CREATE INDEX fk_quote_reactions_quote_id ON quote_reactions (quote_id);

ALTER TABLE quote_reactions
    ADD CONSTRAINT fk_quote_reactions_user_id FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE;

ALTER TABLE quotes
    ADD CONSTRAINT fk_quotes_user_id FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE;

CREATE INDEX fk_quotes_user_id ON quotes (user_id);

ALTER TABLE saved_quotes
    ADD CONSTRAINT fk_saved_quotes_quote_id FOREIGN KEY (quote_id) REFERENCES quotes (quote_id) ON DELETE CASCADE;

CREATE INDEX fk_saved_quotes_quote_id ON saved_quotes (quote_id);

ALTER TABLE saved_quotes
    ADD CONSTRAINT fk_saved_quotes_user_id FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE;

ALTER TABLE user_roles
    ADD CONSTRAINT fk_user_roles_role_id FOREIGN KEY (role_id) REFERENCES roles (role_id) ON DELETE CASCADE;

CREATE INDEX fk_user_roles_role_id ON user_roles (role_id);

ALTER TABLE user_roles
    ADD CONSTRAINT fk_user_roles_user_id FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE;