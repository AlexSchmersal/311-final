import java.util.HashMap;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SimpleName;

public class MethodVisitor extends ASTVisitor {
	
	MethodDeclaration constructor;
	HashMap<Integer,SimpleName> recordedMethods = new HashMap<Integer,SimpleName>();
	
	public MethodVisitor() {
		
	}
	
	public boolean visit(MethodDeclaration node) {
		if (node.isConstructor())
			constructor = node;
		recordedMethods.put(node.getBody().toString().hashCode(), node.getName());
		return false;
	}
}
