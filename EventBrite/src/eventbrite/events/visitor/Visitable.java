package eventbrite.events.visitor;


/**
 * The Visitable is an interface that declares accept() which allows any visitor to pass the operations it wants to
 * on the correct object
 **/

public interface Visitable {
     double accept(Visitor visitor);
}
