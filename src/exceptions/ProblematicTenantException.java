package exceptions;

import people.Person;

public class ProblematicTenantException extends RuntimeException{
    public ProblematicTenantException(Person p) {
        super(p.getFirstName() + " " + p.getLastName() +
                " had already renting rooms: ");
        p.showRented();
    }
}
