import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Assignment;

public class ASTVisitorExtended extends ASTVisitor {
	public boolean visit(Assignment node) {
		System.out.println(node.getRightHandSide());
		System.out.println(node.getOperator());
		return false;
	}
}
