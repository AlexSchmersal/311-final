import java.util.HashMap;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.MethodDeclaration;

public class MethodVisitor extends ASTVisitor {
	
	HashMap<Integer,Block> recordedMethods = new HashMap<Integer,Block>();
	
	public MethodVisitor() {
		
	}
	
	public boolean visit(MethodDeclaration node) {
		System.out.println("Method declared: " + node.getBody().toString());
		recordedMethods.put(node.getBody().hashCode(), node.getBody());
		return false;
	}
}
