/*
package com.daily_notes.notes.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.DataCenterReplication;

import java.util.Collections;
import java.util.List;

@Configuration
public class CassandraConfig extends AbstractCassandraConfiguration {

    @Override
    protected String getKeyspaceName() {
        return "my_keyspace";
    }

    @Override
    public String[] getEntityBasePackages() {
        return new String[]{
                "com.daily_notes.notes.entity"
        };
    }

    @Override
    protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {

        return Collections.singletonList(
                CreateKeyspaceSpecification
                        .createKeyspace(getKeyspaceName())
                        .ifNotExists()
                        .withNetworkReplication(
                                DataCenterReplication.of(
                                        "datacenter1",
                                        1
                                )
                        )
        );
    }

    @Override
    public SchemaAction getSchemaAction() {
        // Automatically builds tables and resolves missing columns based on your entities
        return SchemaAction.RECREATE_DROP_UNUSED;
    }
}*/
