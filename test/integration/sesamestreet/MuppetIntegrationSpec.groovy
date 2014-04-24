package sesamestreet

import grails.test.spock.IntegrationSpec
import spock.lang.*

class MuppetIntegrationSpec extends IntegrationSpec {
	
	def "test save ernie"(){
		given:
			def ernie = new Muppet(name: 'ernie', color: 'orange', gender: Muppet.MALE)
			def initialSize = Muppet.count
		when:
			ernie.save()
		then:
			Muppet.count == initialSize + 1
	}
	
	def "test save bert"(){
		given:
			def bert = new Muppet(name: 'bert', gender: Muppet.MALE)
			def initialSize = Muppet.count
		when:
			bert.save()
		then:
			bert.errors.errorCount == 1
			Muppet.count == initialSize 
	}
	
}


