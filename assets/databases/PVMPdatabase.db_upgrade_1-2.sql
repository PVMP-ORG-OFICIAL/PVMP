CREATE TABLE PropositionFeedback (
 user_name TEXT NOT NULL,
 id_prop INTEGER NOT NULL,
 opinion TEXT NOT NULL,
 CONSTRAINT PropositionFeedback_pk PRIMARY KEY (user_name, id_prop)
)