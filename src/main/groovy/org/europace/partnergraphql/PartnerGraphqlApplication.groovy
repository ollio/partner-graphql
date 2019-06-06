package org.europace.partnergraphql

import gql.DSL
import graphql.GraphQL
import graphql.schema.GraphQLObjectType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

import javax.annotation.PostConstruct

@SpringBootApplication
class PartnerGraphqlApplication {

	@Autowired
	PartnerRepository partnerRepository

	GraphQLObjectType partner = DSL.type('Partner') {
		field 'id', GraphQLString
		field 'vorname', GraphQLString
		field 'nachname', GraphQLString
	}


	def schema = DSL.schema {
		queries {
			field('partner') {
				type partner
				argument 'id', GraphQLString
				argument 'vorname', GraphQLString
				fetcher { env ->
					switch (env.arguments) {
						case {it.id}: return partnerRepository.findById(env.arguments.id)
						case {it.vorname}: return partnerRepository.findByVorname(env.arguments.vorname)
					}
//					partnerRepository.findById(env.arguments.id)
				}
			}
		}
	}

	private GraphQL graphQL

	@Bean
	GraphQL graphQL() {
		return graphQL
	}

	@PostConstruct
	void init() throws IOException {
		this.graphQL = GraphQL.newGraphQL(schema).build()
	}

	static void main(String[] args) {
		SpringApplication.run(PartnerGraphqlApplication, args)
	}

}
