package gtd.grammar;

import gtd.grammar.structure.Alternative;
import gtd.grammar.structure.IStructure;
import gtd.grammar.structure.Scope;
import gtd.grammar.symbols.Char;
import gtd.grammar.symbols.Literal;
import gtd.grammar.symbols.PlusList;
import gtd.grammar.symbols.RSort;
import gtd.grammar.symbols.Sort;

public class FiltersSpec {
	public final static Class<?>[] IMPORTS = new Class<?>[] {
		LayoutSpec.class,
		TypesSpec.class
	};

	public static IStructure[] FilterSpecification() {
		return new IStructure[] {
			new Scope(
				new Alternative(new Sort("Character")),
				new Alternative(new Sort("CharacterRanges")),
				new Alternative(new Sort("String"))
			),
			new Alternative(new Char('{'), new Sort("OptionalSpaces"), new PlusList(new RSort("FilterSpecification"), new Sort("OptionalSpaces"), new Char(','), new Sort("OptionalSpaces")), new Sort("OptionalSpaces"), new Char('}'))
		};
	}

	public static IStructure[] BeforeFilter() {
		return new IStructure[] {
			new Alternative(new Sort("PrecedeRestriction")),
			new Alternative(new Sort("PrecedeRequirement"))
		};
	}

	public static IStructure[] PrecedeRestriction() {
		return new IStructure[] {
			new Alternative(new Sort("FilterSpecification"), new Sort("OptionalSpaces"), new Literal("<-!"))
		};
	}

	public static IStructure[] PrecedeRequirement() {
		return new IStructure[] {
			new Alternative(new Sort("FilterSpecification"), new Sort("OptionalSpaces"), new Literal("<-="))
		};
	}

	public static IStructure[] AfterFilter() {
		return new IStructure[] {
			new Alternative(new Sort("FollowRestriction")),
			new Alternative(new Sort("FollowRequirement")),
			new Alternative(new Sort("MatchRestriction"))
		};
	}

	public static IStructure[] FollowRestriction() {
		return new IStructure[] {
			new Alternative(new Literal("!->"), new Sort("OptionalSpaces"), new Sort("FilterSpecification"))
		};
	}

	public static IStructure[] FollowRequirement() {
		return new IStructure[] {
			new Alternative(new Literal("=->"), new Sort("OptionalSpaces"), new Sort("FilterSpecification"))
		};
	}

	public static IStructure[] MatchRestriction() {
		return new IStructure[] {
			new Alternative(new Literal("!="), new Sort("OptionalSpaces"), new Sort("FilterSpecification"))
		};
	}
}
