package sesamestreet

import grails.test.mixin.*
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(MuppetDiscoveryController)
@Mock([MuppetDiscoveryService, Muppet])
class MuppetDiscoveryControllerSpec extends Specification {

    def setup() {
		new Muppet(name: 'Ernie', color: "orange", hasFur: false, gender: Muppet.MALE).save()
    }

    def "test Found Perfect Muppet"() {
		given:
			request.method = 'POST'
			def params = [color: 'orange', hasFur: false ]
			controller.params.putAll(params)
		when:
			controller.search()
		then:		
			view == '/muppetDiscovery/found'  
			flash.msg.startsWith('Congrats')
			model.muppet.name == 'Ernie'
    }
	
	def "test Not Found Perfect Muppet"() {
		given:
			request.method = 'POST'
			def params = [color: 'yellow', hasFur: false ]
			controller.params.putAll(params)
		when:
			controller.search()
		then:
			view == '/muppetDiscovery/notfound'
			flash.msg.startsWith('Sorry')
			model.muppets.size() == 1
	}
}

