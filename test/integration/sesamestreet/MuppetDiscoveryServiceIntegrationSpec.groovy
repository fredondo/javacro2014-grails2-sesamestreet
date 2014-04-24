package sesamestreet

import grails.test.spock.IntegrationSpec
import spock.lang.*

class MuppetDiscoveryServiceIntegrationSpec extends IntegrationSpec {
	
	def muppetDiscoveryService

	def setup() {
		def muppets = [
			new Muppet(name: 'Ernie', color: "orange", hasFur: false, gender: Muppet.MALE),
			new Muppet(name: 'Bert', color: "yellow", hasFur: false, gender: Muppet.MALE),
			new Muppet(name: 'Elmo', color: "red", hasFur: true, gender: Muppet.MALE),
			new Muppet(name: 'Grover', color: "blue", hasFur: true, gender: Muppet.MALE),
			new Muppet(name: 'Cookie Monster', color: "blue", hasFur: true, gender: Muppet.MALE),
			new Muppet(name: 'Zoe', color:"orange", hasFur: true, gender: Muppet.FEMALE)
		]
		muppets.each{
			it.save()
		}
    }

    void "test Look for Perfect and Related Muppets"() {
		given:
			def desiredTraits = new Muppet(color: "orange",  hasFur: false)
		when:	
			def perfectMuppet = muppetDiscoveryService.lookForYourPerfectMuppet(desiredTraits)
			def relatedMuppets = muppetDiscoveryService.lookForRelatedMuppets(desiredTraits)
		then:
			perfectMuppet != null
			perfectMuppet.name == 'Ernie'
			relatedMuppets.size() == 3
    }
}


