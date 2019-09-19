CREATE TABLE `comment` (
	`id` BIGINT NOT NULL AUTO_INCREMENT,
	`parent_id` BIGINT DEFAULT '0' COMMENT '被回复的问题/评论id',
	`type` INT DEFAULT '0' COMMENT '被回复的类型:问题:1,回复:2',
	`content` VARCHAR(1024) DEFAULT NULL COMMENT '评论/回复的内容',
	`commentator` BIGINT DEFAULT '0' COMMENT '评论人id',
	`gmt_create` BIGINT DEFAULT '0' COMMENT '创建时间',
	`gmt_modified` BIGINT DEFAULT '0' COMMENT '更新时间',
	`like_count` INT DEFAULT '0' COMMENT '点赞数',
	`comment_count` INT DEFAULT '0' COMMENT '评论数',
	PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 CHARSET=utf8;