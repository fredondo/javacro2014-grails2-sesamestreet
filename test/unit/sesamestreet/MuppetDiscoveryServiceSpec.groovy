package sesamestreet

import grails.test.mixin.*
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(MuppetDiscoveryService)
@Mock(Muppet)
class MuppetDiscoveryServiceSpec extends Specification {

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
			def perfectMuppet = service.lookForYourPerfectMuppet(desiredTraits)
			def relatedMuppets = service.lookForRelatedMuppets(desiredTraits)
		then:
			perfectMuppet != null
			perfectMuppet.name == 'Ernie'
			relatedMuppets.size() == 3
    }
}

