import org.eclipse.jdt.core.dom.CompilationUnit;

public class Strategies {
	CompilationUnit source1;
	CompilationUnit source2;
	
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
	
	public boolean simplifiedACF() {
		return false;
	}
	
	public boolean simplifiedSimilarity() {
		return false;
	}
}
