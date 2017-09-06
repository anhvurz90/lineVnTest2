ALTER TABLE user ADD COLUMN total_chat int, ADD COLUMN latest_chat varchar(255) DEFAULT NULL;
UPDATE user u SET total_chat = (SELECT COUNT(*) FROM chat c WHERE c.status in (1,2) AND c.owner_id = u.id);
UPDATE user u SET latest_chat = (SELECT title FROM chat c WHERE c.status in (1,2) AND c.owner_id = u.id order by c.id limit 1);

CREATE INDEX idx_user_loc_id ON user(location_id);
CREATE INDEX idx_user_name ON user(username);
CREATE INDEX idx_user_l_chat ON user(latest_chat);

CREATE INDEX idx_loc_parent_id ON location(parent_id);
CREATE INDEX idx_loc_name ON location(name);
