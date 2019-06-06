# partner-graphql

Example Groovy project for using GQL with GraphQL Java. 

## Usage

1. Start spring boot app
2. Open http://localhost:8080/graphql with ChromeiQL
3. call: {
  partner(id:"WER03")
  {
    vorname
    nachname
    id
  }
}
