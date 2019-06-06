package org.europace.partnergraphql

import gql.DSL
import org.junit.Test

class GraphQLTest {

  def GraphQLFilm = DSL.type('Film') {
    field 'title', GraphQLString
    field 'year', GraphQLInt
  }

  def schema = DSL.schema {
    queries {
      field('lastFilm') {
        type GraphQLFilm
        staticValue(title: 'SPECTRE', year: 2015)
      }
    }
  }

  def query = """
  {
    lastFilm {
      year
      title
    }
  }
"""

  @Test
  void executeQuery() {
    def result = DSL.execute(schema, query)

    assert result.data.lastFilm.year == 2015
    assert result.data.lastFilm.title == 'SPECTRE'

  }
}
