CREATE TABLE api.documentlist (
id SERIAL PRIMARY KEY,
page INT,
nbElements INT
);

CREATE TABLE api.document(
documentid SERIAL PRIMARY KEY,
created TIMESTAMP (3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
updated TIMESTAMP (3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
title VARCHAR(255),
creator VARCHAR(255),
editor VARCHAR(255),
body VARCHAR(3000),
statusdocument VARCHAR(255),
documentlist_id  BIGINT UNSIGNED NOT NULL,
CONSTRAINT FK_document_documentlist_id FOREIGN KEY (documentlist_id) REFERENCES api.documentlist(id)
);

CREATE TABLE api.errordefinition (
id SERIAL PRIMARY KEY,
errorType VARCHAR(255)
);

CREATE TABLE api.error (
id SERIAL PRIMARY KEY,
errorCode VARCHAR(255),
errorMessage VARCHAR(255),
errordefinition_id  BIGINT UNSIGNED NOT NULL,
CONSTRAINT FK_error_errordefinition_id FOREIGN KEY (errordefinition_id) REFERENCES api.errordefinition(id)
);

CREATE TABLE api.lock (
id SERIAL PRIMARY KEY,
owner VARCHAR(255),
created VARCHAR(255)
);
