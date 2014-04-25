package sesamestreet

import spock.lang.*
import grails.test.spock.IntegrationSpec

class MuppetDiscoveryControllerIntegrarionSpec extends IntegrationSpec {

    def setup() {
		new Muppet(name: 'Ernie', color: "orange", hasFur: false, gender: Muppet.MALE).save()
    }

    def "test Found Perfect Muppet"() {
		given:
			def controller = new MuppetDiscoveryController()
		and:
			controller.request.method = 'POST'
			def params = [color: 'orange', hasFur: false ]
			controller.params.putAll(params)
		when:
			controller.search()
		then:		
			controller.modelAndView.viewName == '/muppetDiscovery/found'  
			controller.flash.msg.startsWith('Congrats')
			controller.modelAndView.model.muppet.name == 'Ernie'
    }
	
	def "test Not Found Perfect Muppet"() {
		given:
			def controller = new MuppetDiscoveryController()
		and:
			controller.request.method = 'POST'
			def params = [color: 'yellow', hasFur: false ]
			controller.params.putAll(params)
		when:
			controller.search()
		then:
			controller.modelAndView.viewName  == '/muppetDiscovery/notfound'
			controller.flash.msg.startsWith('Sorry')
			controller.modelAndView.model.muppets.size() == 1
	}
}





