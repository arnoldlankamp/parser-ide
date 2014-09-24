package gtd.grammar;

import gtd.grammar.structure.Alternative;
import gtd.grammar.structure.IStructure;
import gtd.grammar.symbols.Char;
import gtd.grammar.symbols.PlusList;
import gtd.grammar.symbols.Sort;
import gtd.grammar.symbols.StarList;

public class LayoutSpec {

	public static IStructure[] Whitespace() {
		return new IStructure[] {
			new Alternative(new Char(' ')),
			new Alternative(new Char('\t')),
			new Alternative(new Char('\r')),
			new Alternative(new Char('\n'))
		};
	}
	
	public static IStructure[] Layout() {
		return new IStructure[] {
			new Alternative(new StarList(new Sort("Whitespace")))
		};
	}

	public static IStructure[] OptionalSpaces() {
		return new IStructure[] {
			new Alternative(new StarList(new Char(' ')))
		};
	}

	public static IStructure[] RequiredSpaces() {
		return new IStructure[] {
			new Alternative(new PlusList(new Char(' ')))
		};
	}

	public static IStructure[] EndLine() {
		return new IStructure[] {
			new Alternative(new Char('\n')),
			new Alternative(new Char('\r'), new Char('\n'))
		};
	}

	public static IStructure[] EscapedEndLine() {
		return new IStructure[] {
			new Alternative(new Char('\\'), new Sort("OptionalSpaces"), new Char('\n')),
			new Alternative(new Char('\\'), new Sort("OptionalSpaces"), new Char('\r'), new Char('\n'))
		};
	}
}
