package gtd.grammar;

import gtd.grammar.structure.Alternative;
import gtd.grammar.structure.IStructure;
import gtd.grammar.symbols.Char;
import gtd.grammar.symbols.Choice;
import gtd.grammar.symbols.Literal;
import gtd.grammar.symbols.Optional;
import gtd.grammar.symbols.PlusList;
import gtd.grammar.symbols.Sequence;
import gtd.grammar.symbols.Sort;

public class SyntaxSpec {
	public final static Class<?>[] IMPORTS = new Class<?>[] {
		LayoutSpec.class,
		TypesSpec.class,
		ComposedTypesSpec.class
	};

	public static IStructure[] Modifier() {
		return new IStructure[] {
			new Alternative(new Literal("syntax")),
			new Alternative(new Literal("layout"))
		};
	}

	public static IStructure[] Associativity() {
		return new IStructure[] {
			new Alternative(new Literal("left")),
			new Alternative(new Literal("right")),
			new Alternative(new Literal("restrict")),
			new Alternative(new Literal("allow"))
		};
	}

	public static IStructure[] ScopeModifier() {
		return new IStructure[] {
			new Alternative(new Char('&')),
			new Alternative(new Char('<'))
		};
	} 

	public static IStructure[] Scope() {
		return new IStructure[] {
			new Alternative(new Optional(new Sort("ScopeModifier")), new Sort("OptionalSpaces"), new Char('('), new Sort("Layout"), new PlusList(new Sort("RHS"), new Sort("Layout")), new Sort("Layout"), new Char(')'), new Sort("OptionalSpaces"), new Optional(new Sort("Associativity")))
		};
	}

	public static IStructure[] RHS() {
		return new IStructure[] {
			new Alternative(new PlusList(new Sort("Symbol"), new Choice(new Sort("RequiredSpaces"), new Sequence(new Sort("OptionalSpaces"), new Sort("EscapedEndLine"), new Sort("OptionalSpaces"))))),
			new Alternative(new Sort("Scope"))
		};
	}

	public static IStructure[] KeywordRHS() {
		return new IStructure[] {
			new Alternative(new Sort("String"))
		};
	}

	public static IStructure[] Rule() {
		return new IStructure[] {
			new Alternative(new Optional(new Sequence(new Sort("Modifier"), new Sort("RequiredSpaces"))), new Sort("Sort"), new Sort("OptionalSpaces"), new Char('='), new Sort("Layout"), new PlusList(new Sort("RHS"), new Sort("Layout"))),
			new Alternative(new Optional(new Sequence(new Sort("Modifier"), new Sort("RequiredSpaces"))), new Sort("Sort"), new Sort("OptionalSpaces"), new Literal("=="), new Sort("Layout"), new PlusList(new Sort("RHS"), new Sort("Layout"))),
			new Alternative(new Literal("keyword"), new Sort("RequiredSpaces"), new Sort("Sort"), new Sort("OptionalSpaces"), new Char('='), new Sort("Layout"), new PlusList(new Sort("KeywordRHS"), new Sort("Layout"))),
			new Alternative(new Literal("keyword"), new Sort("RequiredSpaces"), new Sort("Sort"), new Sort("OptionalSpaces"), new Literal("=="), new Sort("Layout"), new PlusList(new Sort("KeywordRHS"), new Sort("Layout")))
		};
	}

	public static IStructure[] Grammar() {
		return new IStructure[] {
			new Alternative(new Sort("Layout"), new PlusList(new Sort("Rule"), new Sort("Layout")), new Sort("Layout"))
		};
	}
}
