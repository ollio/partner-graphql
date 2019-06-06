package org.europace.partnergraphql

import org.springframework.stereotype.Component

@Component
class PartnerRepository {

  static Map<String, Partner> storage = [WER03: new Partner(
      id: 'WER03',
      vorname: "Werner",
      nachname: "Wernerson"
  ), QAY01                             : new Partner(
      id: 'QAY01',
      vorname: "Max",
      nachname: "Musterman"
  )]

  Partner findById(String partnerId) {
    storage.get(partnerId)
  }

  Partner findByVorname(String vorname) {
    storage.values().find {it.vorname == vorname}
  }
}
