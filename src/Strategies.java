import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.SimpleName;
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
		int source1Hash = source1.toString().hashCode();
		int source2Hash = source2.toString().hashCode();
		System.out.println(source1Hash + " vs " + source2Hash);
		if (source1Hash == source2Hash)
			return true;
		return false;
	}
	
	private int hashNameMethods(int nameHash, Collection<SimpleName> source1MethodsSet) {
		int returnHash = 0;
		
		for (SimpleName hash : source1MethodsSet)
			returnHash += hash.toString().hashCode();
		
		returnHash += nameHash;
		
		return returnHash;
	}
	
	//Generates hash of sources class name and method names, then compares the classes
	public boolean simplifiedACF() {
		source1.accept(this.source1Methods);
		source2.accept(this.source2Methods);
		
		int source1ConstructorHash = this.source1Methods.constructor.getName().toString().hashCode();
		int source2ConstructorHash = this.source2Methods.constructor.getName().toString().hashCode();
		Collection<SimpleName> source1MethodsSet = this.source1Methods.recordedMethods.values();
		Collection<SimpleName> source2MethodsSet = this.source2Methods.recordedMethods.values();
		
		return(hashNameMethods(source1ConstructorHash, source1MethodsSet) == hashNameMethods(source2ConstructorHash, source2MethodsSet));
	}
	private int hashBody(int bodyHash, Collection<Block> source1BodySet) {
		int returnHash = 0;
		
		for (Block hash : source1BodySet)
			returnHash += hash.toString().hashCode();
		
		returnHash += bodyHash;
		
		return returnHash;
	}
	
	public boolean simplifiedSimilarity() {
		source1.accept(this.source1Methods);
		source2.accept(this.source2Methods);
		
		int source1ConstructorHash = this.source1Methods.constructor.getName().toString().hashCode();
		int source2ConstructorHash = this.source2Methods.constructor.getName().toString().hashCode();
		Collection<Block> source1BodySet = this.source1Methods.recordedBody.values();
		Collection<Block> source2BodySet = this.source2Methods.recordedBody.values();
		
		return((hashBody(source1ConstructorHash, source1BodySet) == hashBody(source2ConstructorHash, source2BodySet)));
		

	}
	
}
