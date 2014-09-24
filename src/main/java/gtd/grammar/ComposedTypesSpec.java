package gtd.grammar;

import gtd.grammar.structure.Alternative;
import gtd.grammar.structure.IStructure;
import gtd.grammar.structure.Scope;
import gtd.grammar.symbols.Char;
import gtd.grammar.symbols.Literal;
import gtd.grammar.symbols.PlusList;
import gtd.grammar.symbols.RSort;
import gtd.grammar.symbols.Sort;

public class ComposedTypesSpec {
	public final static Class<?>[] IMPORTS = new Class<?>[] {
		LayoutSpec.class,
		FiltersSpec.class
	};

	public static IStructure[] Symbol() {
		return new IStructure[] {
			new Scope(
				new Alternative(new Sort("Empty")),
				new Alternative(new Sort("Character")),
				new Alternative(new Sort("CharacterRanges")),
				new Alternative(new Sort("String")),
				new Alternative(new Sort("CaseInsensitiveString")),
				new Alternative(new Sort("Sort")),
				new Alternative(new Sort("RestrictedSort")),
				new Alternative(new Sort("AbstractList")),
				new Alternative(new Sort("Optional")),
				new Alternative(new Sort("Sequence")),
				new Alternative(new Sort("Choice"))
			),
			new Alternative(new Sort("BeforeFilter"), new Sort("OptionalSpaces"), new RSort("Symbol")),
			new Alternative(new RSort("Symbol"), new Sort("OptionalSpaces"), new Sort("AfterFilter"))
		};
	}

	public static IStructure[] RestrictedSort() {
		return new IStructure[] {
			new Alternative(new Char('-'), new Sort("Sort"))
		};
	}

	public static IStructure[] AbstractList() {
		return new IStructure[] {
			new Alternative(new Sort("List")),
			new Alternative(new Sort("EagerList")),
			new Alternative(new Sort("SeparatedList"))
		};
	}

	public static IStructure[] ListTypeSymbol() {
		return new IStructure[] {
			new Alternative(new Char('*')),
			new Alternative(new Char('+'))
		};
	}

	public static IStructure[] List() {
		return new IStructure[] {
			new Alternative(new Sort("Symbol"), new Sort("ListTypeSymbol"))
		};
	}

	public static IStructure[] EagerList() {
		return new IStructure[] {
			new Alternative(new Sort("Symbol"), new Sort("ListTypeSymbol"), new Literal("->"))
		};
	}

	public static IStructure[] SeparatedList() {
		return new IStructure[] {
			new Alternative(new Sort("Symbol"), new Char('{'), new PlusList(new Sort("Symbol"), new Sort("OptionalSpaces"), new Char(','), new Sort("OptionalSpaces")), new Sort("OptionalSpaces"), new Char('}'), new Sort("ListTypeSymbol"))
		};
	}

	public static IStructure[] Optional() {
		return new IStructure[] {
			new Alternative(new Sort("Symbol"), new Char('?'))
		};
	}

	public static IStructure[] Sequence() {
		return new IStructure[] {
			new Alternative(new Char('{'), new Sort("OptionalSpaces"), new PlusList(new Sort("Symbol"), new Sort("RequiredSpaces")), new Sort("OptionalSpaces"), new Char('}'))
		};
	}

	public static IStructure[] Choice() {
		return new IStructure[] {
			new Alternative(new Char('{'), new Sort("OptionalSpaces"), new PlusList(new Sort("Symbol"), new Sort("OptionalSpaces"), new Char('|'), new Sort("OptionalSpaces")), new Sort("OptionalSpaces"), new Char('}'))
		};
	}
}
