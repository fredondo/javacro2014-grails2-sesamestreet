package sesamestreet

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Muppet)
class MuppetSpec extends Specification {


	def "Ernie validates OK"() {
		when:
			def ernie = new Muppet(name: 'ernie', gender: Muppet.MALE, color: 'orange')
		then:
			ernie.validate()
	}

	def "Bert fails on validate"() {
		when:
			def bert = new Muppet(name: 'bert', gender: Muppet.MALE)
		then:
			!bert.validate()
	}
	
	def "validate several of them"() {
		expect:
			new Muppet([name: name, color: color, gender: gender]).validate() == result
		where:
			name    | gender   		 | color	| result
			'ernie' | Muppet.MALE    | 'orange' | true
			'bert'  | Muppet.MALE    |  null	| false
			'elmo'  | Muppet.MALE    | 'red'	| true
			'zoe'   | Muppet.FEMALE  | 'orange'	| true
	  }
	
}
