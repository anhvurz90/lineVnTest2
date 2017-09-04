ALTER TABLE user ADD COLUMN total_chat int, ADD COLUMN latest_chat varchar(255) DEFAULT NULL;
UPDATE user u SET total_chat = (SELECT COUNT(*) FROM chat c WHERE c.status in (1,2) AND c.owner_id = u.id);
UPDATE user u SET latest_chat = (SELECT title FROM chat c WHERE c.status in (1,2) AND c.owner_id = u.id order by c.id limit 1);
