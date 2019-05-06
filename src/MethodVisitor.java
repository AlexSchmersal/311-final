import java.util.HashMap;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SimpleName;

import com.sun.xml.internal.ws.wsdl.writer.document.soap.Body;

public class MethodVisitor extends ASTVisitor {
	
	MethodDeclaration constructor;
	HashMap<Integer,SimpleName> recordedMethods = new HashMap<Integer,SimpleName>();
	HashMap<Integer,Block> recordedBody = new HashMap<Integer,Block>();
	public MethodVisitor() {
		
	}
	
	public boolean visit(MethodDeclaration node) {
		if (node.isConstructor())
			constructor = node;
		recordedMethods.put(node.getBody().toString().hashCode(), node.getName());
		recordedBody.put(node.getBody().toString().hashCode(), node.getBody());
		return false;
	}
}
