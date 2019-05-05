import java.util.ArrayList;
import java.util.Set;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class Strategies {
	CompilationUnit source1;
	CompilationUnit source2;
	MethodVisitor source1Methods = new MethodVisitor();
	MethodVisitor source2Methods = new MethodVisitor();
	
	public Strategies(CompilationUnit source1, CompilationUnit source2) {
		this.source1 = source1;
		this.source2 = source2;
	}
	
	public boolean exactMatch() {
		int source1Hash = source1.hashCode();
		int source2Hash = source2.hashCode();
		System.out.println(source1Hash + " vs " + source2Hash);
		if (source1Hash == source2Hash)
			return true;
		return false;
	}
	
	//Generates hash of sources class name and method signatures, then compares the classes
	public boolean simplifiedACF() {
		source1.accept(this.source1Methods);
		source2.accept(this.source2Methods);
		
		Set<Integer> keys = this.source1Methods.recordedMethods.keySet();
		for (Integer key : keys)
			System.out.println(key);
		Set<Integer> keys2 = this.source2Methods.recordedMethods.keySet();
		for (Integer key : keys2)
			System.out.println(key);
		//TODO: Why are these keys different when their bodies are the same??
		return false;
	}
	
	public boolean simplifiedSimilarity() {
		return false;
	}
}
