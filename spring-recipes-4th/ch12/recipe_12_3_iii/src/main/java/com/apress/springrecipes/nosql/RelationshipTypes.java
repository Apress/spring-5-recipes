package com.apress.springrecipes.nosql;

import org.neo4j.graphdb.RelationshipType;

/**
 * Created by marten on 10-10-14.
 */
public enum RelationshipTypes implements RelationshipType {
    FRIENDS_WITH, MASTER_OF, LOCATION
}
