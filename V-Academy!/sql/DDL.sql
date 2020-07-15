#DROP SCHEMA `cs5200_spring18_course_mgmt`;
CREATE SCHEMA `cs5200_spring18_course_mgmt`;

#CREATE SCHEMA `testing`;

USE `cs5200_spring18_course_mgmt`;

CREATE TABLE `Person`(
  `personId` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL,
  `email` VARCHAR(100) NOT NULL,
  `username` VARCHAR(100) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `createDate` DATETIME NULL DEFAULT NOW(),
  `updateDate` DATETIME NULL,
  PRIMARY KEY (`personId`));
  

CREATE TABLE `Phone` (
  `personId` INT NOT NULL,
  `phoneId` INT NOT NULL AUTO_INCREMENT,
  `countryCode` INT NULL,
  `areaCode` INT NULL,
  `phoneNumber` VARCHAR(20) NOT NULL,
  `primary` TINYINT NULL,
  PRIMARY KEY (`phoneId`),
  INDEX `fk_phone_person_idx` (`personId` ASC),
  CONSTRAINT `fk_phone_person`
    FOREIGN KEY (`personId`)
    REFERENCES `Person` (`personId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE `Administrator` (
  `personId` INT(11) NOT NULL,
  `adminId`  INT(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`adminId`),
  INDEX `fk_administrator_person_idx` (`personId` ASC),
  UNIQUE INDEX `personid_UNIQUE`(`personId` ASC),
  CONSTRAINT `fk_Administrator_Person`
    FOREIGN KEY (`personId`)
    REFERENCES `Person` (`personId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);


CREATE TABLE `Faculty` (
  `personId` INT NOT NULL,
  `facultyId` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`facultyId`),
  INDEX `fk_faculty_person_idx` (`personId` ASC),
  UNIQUE INDEX `personId_UNIQUE` (`personId` ASC),
  CONSTRAINT `fk_faculty_person`
    FOREIGN KEY (`personId`)
    REFERENCES `Person` (`personId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE `Student` (
  `personId` INT NOT NULL,
  `studentId` INT NOT NULL AUTO_INCREMENT,
  `createdBy` INT NULL,
  PRIMARY KEY (`studentId`),
  UNIQUE INDEX `personId_UNIQUE` (`personId` ASC),
  INDEX `fk_student_createdby_person_idx` (`createdBy` ASC),
  CONSTRAINT `fk_student_person`
    FOREIGN KEY (`personId`)
    REFERENCES `Person` (`personId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_student_createdby_person`
    FOREIGN KEY (`createdBy`)
    REFERENCES `Person` (`personId`)
    ON DELETE SET NULL
    ON UPDATE CASCADE);

CREATE TABLE `UserAction` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC));


CREATE TABLE `Role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC));
  
CREATE TABLE `UserMapping` (
  `privilege` INT NOT NULL,
  `targetUser` INT NOT NULL,
  `sourceUser` INT NOT NULL,
  INDEX `fk_UserMapping_useraction_idx` (`privilege` ASC),
  INDEX `fk_userMapping_source_person_idx` (`sourceUser` ASC),
  INDEX `fk_usermapping_target_person_idx` (`targetUser` ASC),
  CONSTRAINT `fk_usermapping_useraction`
    FOREIGN KEY (`privilege`)
    REFERENCES `UserAction` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_usermapping_source_person`
    FOREIGN KEY (`sourceUser`)
    REFERENCES `Person` (`personId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_usermapping_target_person`
    FOREIGN KEY (`targetUser`)
    REFERENCES `Person` (`personId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE `Course` (
  `courseId` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `identifier` VARCHAR(50) NOT NULL,
  `description` VARCHAR(500) NULL,
  `createDate` DATETIME NOT NULL DEFAULT NOW(),
  `updateDate` DATETIME NULL,
  `displayGridOrder` INT  DEFAULT NULL,
  PRIMARY KEY (`courseId`));

CREATE TABLE `CourseRole` (
  `role` INT NOT NULL,
  `courseId` INT NOT NULL,
  `personId` INT NOT NULL,
  INDEX `fk_courserole_role_idx` (`role` ASC),
  INDEX `fk_courserole_course_idx` (`courseId` ASC),
  INDEX `fk_courserole_person_idx` (`personId` ASC),
  CONSTRAINT `fk_courserole_role`
    FOREIGN KEY (`role`)
    REFERENCES `Role` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_courserole_course`
    FOREIGN KEY (`courseId`)
    REFERENCES `Course` (`courseId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_courserole_person`
    FOREIGN KEY (`personId`)
    REFERENCES `Person` (`personId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE `Theme` (
  `themeId` INT(11) NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `description` VARCHAR(500) DEFAULT NULL,
  `version` VARCHAR(50) DEFAULT NULL,
  `createDate` DATETIME NOT NULL DEFAULT NOW(),
  `updateDate` DATETIME DEFAULT NULL,
  `noOfUses` INT(11) DEFAULT 0,
  `themeBackgroundImage` BLOB,
  `stylesheetLink` VARCHAR(500) DEFAULT NULL,
  PRIMARY KEY (`themeId`));

  
  ################
  CREATE TABLE `Layout` (
  `layoutId` INT NOT NULL AUTO_INCREMENT,
  `courseId` INT NOT NULL,
  `themeId` INT NULL,
  `name` VARCHAR(100) NULL,
  `title` VARCHAR(200) NULL,
  `description` VARCHAR(500) NULL,
  `createDate` DATETIME NOT NULL DEFAULT NOW(),
  `updateDate` DATETIME NULL,
  `customBackground` VARCHAR(500) NULL,
  `stylesheetLink` VARCHAR(500) NULL,
  PRIMARY KEY (`layoutId`),
  INDEX `fk_layout_course_idx` (`courseId` ASC),
  INDEX `fk_layout_theme_idx` (`themeId` ASC),
  CONSTRAINT `fk_layout_course`
    FOREIGN KEY (`courseId`)
    REFERENCES `Course` (`courseId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_layout_theme`
    FOREIGN KEY (`themeId`)
    REFERENCES `Theme` (`themeId`)
    ON DELETE SET NULL
    ON UPDATE CASCADE);

CREATE TABLE `Page` (
  `pageId` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL,
  `tooltipDescription` VARCHAR(500) NULL,
  `createDate` DATETIME NOT NULL DEFAULT NOW(),
  `updateDate` DATETIME NULL,
  `verticalOrder` INT NOT NULL ,
  `layoutId` INT NOT NULL,
  PRIMARY KEY (`pageId`),
  FOREIGN KEY (`layoutId`)
    REFERENCES `Layout` (`layoutId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  UNIQUE KEY `pageId_verticalOrder_UNIQUE` (`pageId` ASC, `verticalOrder` ASC));

CREATE TABLE `LayoutPage` (
  `layoutPageId` INT NOT NULL AUTO_INCREMENT,
  `layoutId` INT NOT NULL,
  `pageId` INT NOT NULL,
  PRIMARY KEY (`layoutPageId`),
  INDEX `layout_page_association_idx` (`pageId` ASC),
  INDEX `page_layout_association_idx` (`layoutId` ASC),
  UNIQUE KEY `layoutId_pageId_UNIQUE` (`layoutId` ASC, `pageId` ASC),
  CONSTRAINT `layout_page_association`
    FOREIGN KEY (`pageId`)
    REFERENCES `Page` (`pageId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `page_layout_association`
    FOREIGN KEY (`layoutId`)
    REFERENCES `Layout` (`layoutId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
    
CREATE TABLE `Tab` (
  `tabId` INT NOT NULL AUTO_INCREMENT,
  `pageId` INT NOT NULL,
  `name` VARCHAR(100) NULL,
  `horizontalOrder` INT NOT NULL,
  PRIMARY KEY (`tabId`),
  UNIQUE KEY `tabId_horizontalOrder_UNIQUE` (`tabId` ASC,`horizontalOrder` ASC),
  INDEX `fk_tab_page_idx` (`pageId` ASC),
  CONSTRAINT `fk_tab_page` 
    FOREIGN KEY (`pageId`) 
    REFERENCES  `Page` (`pageId`) 
    ON DELETE CASCADE 
    ON UPDATE CASCADE);
    
CREATE TABLE `Widget` (
  `widgetId` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL,
  `topPosition` FLOAT NULL,
  `bottomPosition` FLOAT NULL,
  `leftPosition` FLOAT NULL,
  `rightPosition` FLOAT NULL,
  `width` FLOAT NULL,
  `height` FLOAT NULL,
  `foregroundColor` VARCHAR(45) NULL,
  `backgroundColor` VARCHAR(45) NULL,
  `cssClass` VARCHAR(50) NULL,
  `cssStyle` VARCHAR(500) NULL,
  `scrollable` TINYINT NULL,
  `fitContents` TINYINT NULL,
  PRIMARY KEY (`widgetId`));
  
CREATE TABLE `TabWidget` (
  `tabWidgetId` INT NOT NULL AUTO_INCREMENT,
  `tabId` INT NOT NULL,
  `widgetId` INT NOT NULL,
  PRIMARY KEY (`tabWidgetId`),
  INDEX `tab_widget_association_idx` (`widgetId` ASC),
  INDEX `widget_tab_association_idx` (`tabId` ASC),
  UNIQUE KEY `tabId_widgetId_UNIQUE` (`tabId` ASC, `widgetId` ASC),
  CONSTRAINT `tab_widget_association`
    FOREIGN KEY (`widgetId`)
    REFERENCES `Widget` (`widgetId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `widget_tab_association`
    FOREIGN KEY (`tabId`)
    REFERENCES `Tab` (`tabId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
    
  #################  
  
  CREATE TABLE `VerticalAlignment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC));
  
CREATE TABLE `HorizontalAlignment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC));

CREATE TABLE `HtmlWidget` (
  `widgetId` INT(11) NOT NULL,
  `htmlId` INT(11) NOT NULL AUTO_INCREMENT,
  `markupText` TEXT NULL,
  `maxCharacters` INT NULL,
  PRIMARY KEY (`htmlId`),
  INDEX `fk_html_widget_idx` (`widgetId` ASC),
  UNIQUE INDEX `widgetId_UNIQUE` (`widgetId` ASC),
  CONSTRAINT `fk_html_widget`
    FOREIGN KEY (`widgetId`)
    REFERENCES `Widget` (`widgetId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE `GoogleDocWidget` (
  `widgetId` INT NOT NULL,
  `googleDocId` INT NOT NULL AUTO_INCREMENT,
  `url` VARCHAR(500) NULL,
  `editable` TINYINT NULL,
  `type` VARCHAR(45) NULL,
  PRIMARY KEY (`googleDocId`),
  UNIQUE INDEX `widgetId_UNIQUE` (`widgetId` ASC),
  CONSTRAINT `fk_googleDoc_widget`
    FOREIGN KEY (`widgetId`)
    REFERENCES `Widget` (`widgetId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
    
CREATE TABLE `ImageWidget` (
  `widgetId` INT NOT NULL,
  `imageId` INT NOT NULL AUTO_INCREMENT,
  `url` VARCHAR(500) NULL,
  `verticalAlign` VARCHAR(45) NULL,
  `horizontalAlign` VARCHAR(45) NULL,
  PRIMARY KEY (`imageId`),
  UNIQUE INDEX `widgetId_UNIQUE` (`widgetId` ASC),
  INDEX `image_valign_enum_ref_idx` (`verticalAlign` ASC),
  INDEX `image_halign_enuf_ref_idx` (`horizontalAlign` ASC),
  CONSTRAINT `fk_image_widget`
    FOREIGN KEY (`widgetId`)
    REFERENCES `Widget` (`widgetId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `image_valign_enum_ref`
    FOREIGN KEY (`verticalAlign`)
    REFERENCES `VerticalAlignment` (`name`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `image_halign_enuf_ref`
    FOREIGN KEY (`horizontalAlign`)
    REFERENCES `HorizontalAlignment` (`name`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE);

CREATE TABLE `VideoWidget` (
  `widgetId` INT NOT NULL,
  `videoId` INT NOT NULL AUTO_INCREMENT,
  `url` VARCHAR(500) NULL,
  `youtubeId` VARCHAR(45) NULL,
  `expandable` TINYINT NULL,
  PRIMARY KEY (`videoId`),
  UNIQUE INDEX `widgetId_UNIQUE` (`widgetId` ASC),
  CONSTRAINT `fk_video_widget`
    FOREIGN KEY (`widgetId`)
    REFERENCES `Widget` (`widgetId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

  
### INSERT ENUMS
INSERT INTO `UserAction`(`id`, `name`) VALUES(0, 'Create');
INSERT INTO `UserAction`(`id`, `name`) VALUES(2, 'Read');
INSERT INTO `UserAction`(`id`, `name`) VALUES(3, 'Update');
INSERT INTO `UserAction`(`id`, `name`) VALUES(4, 'Delete');

INSERT INTO `Role`(`id`, `name`) VALUES(0, 'Owner');
INSERT INTO `Role`(`id`, `name`) VALUES(2, 'Administrator');
INSERT INTO `Role`(`id`, `name`) VALUES(3, 'Editor');
INSERT INTO `Role`(`id`, `name`) VALUES(4, 'Viewer');
INSERT INTO `Role`(`id`, `name`) VALUES(5, 'Commentor');

INSERT INTO `VerticalAlignment`(`id`, `name`) VALUES(0, 'Top');
INSERT INTO `VerticalAlignment`(`id`, `name`) VALUES(2, 'Bottom');
INSERT INTO `VerticalAlignment`(`id`, `name`) VALUES(3, 'Middle');

INSERT INTO `HorizontalAlignment`(`id`, `name`) VALUES(0, 'Left');
INSERT INTO `HorizontalAlignment`(`id`, `name`) VALUES(2, 'Right');
INSERT INTO `HorizontalAlignment`(`id`, `name`) VALUES(3, 'Center');
