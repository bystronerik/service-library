package com.deizon.services.resolver;

import graphql.kickstart.servlet.context.DefaultGraphQLServletContext;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.schema.DataFetchingEnvironment;

public abstract class BaseResolver implements GraphQLQueryResolver, GraphQLMutationResolver {

    protected String getHeader(String name, DataFetchingEnvironment env) {
        return ((DefaultGraphQLServletContext) env.getContext()).getHttpServletRequest().getHeader(name);
    }

    protected String getClientId(DataFetchingEnvironment env) {
        return this.getHeader("X-Client-Id", env);
    }

}
