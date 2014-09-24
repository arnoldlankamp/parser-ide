package gtd.grammar;

import gtd.grammar.structure.Alternative;
import gtd.grammar.structure.IStructure;
import gtd.grammar.symbols.Char;
import gtd.grammar.symbols.CharRange;
import gtd.grammar.symbols.CharRanges;
import gtd.grammar.symbols.Literal;
import gtd.grammar.symbols.Optional;
import gtd.grammar.symbols.PlusList;
import gtd.grammar.symbols.Sort;
import gtd.grammar.symbols.StarList;
import gtd.stack.filter.after.CharRangeFollowRestriction;

public class TypesSpec {
	public final static Class<?>[] IMPORTS = new Class<?>[] {
		LayoutSpec.class
	};

	public static IStructure[] Empty() {
		return new IStructure[] {
			new Alternative(new Literal("[]")),
			new Alternative(new Literal("''")),
			new Alternative(new Literal("\"\""))
		};
	}

	public static IStructure[] Character() {
		return new IStructure[] {
			new Alternative(new Char('\''), new CharRange(Character.MIN_VALUE, Character.MAX_VALUE), new Char('\'')),
			new Alternative(new Char('\''), new Char('\\'), new Char('\''), new Char('\'')),
			new Alternative(new Char('\''), new Char('\\'), new Char('\\'), new Char('\''))
		};
	}

	public static IStructure[] EscapedCharRangeCharacter() {
		return new IStructure[] {
			new Alternative(new CharRange(Character.MIN_VALUE, 'Z')),
			new Alternative(new CharRange('^', Character.MAX_VALUE)),
			new Alternative(new Char('\\'), new Char('\\')),
			new Alternative(new Char('\\'), new Char('[')),
			new Alternative(new Char('\\'), new Char(']'))
		};
	}

	public static IStructure[] CharacterRange() {
		return new IStructure[] {
			new Alternative(new Sort("EscapedCharRangeCharacter")),
			new Alternative(new Sort("EscapedCharRangeCharacter"), new Char('-'), new Sort("EscapedCharRangeCharacter"))
		};
	}

	public static IStructure[] CharacterRanges() {
		return new IStructure[] {
			new Alternative(new Char('['), new PlusList(new Sort("CharacterRange"), new Optional(new Char(' '))), new Char(']'))
		};
	}

	public static IStructure[] EscapedStringCharacter() {
		return new IStructure[] {
			new Alternative(new CharRange(Character.MIN_VALUE, '!')),
			new Alternative(new CharRange('#', '[')),
			new Alternative(new CharRange(']', Character.MAX_VALUE)),
			new Alternative(new Char('\\'), new Char('\\')),
			new Alternative(new Char('\\'), new Char('"'))
		};
	}

	public static IStructure[] String() {
		return new IStructure[] {
			new Alternative(new Char('"'), new PlusList(new Sort("EscapedStringCharacter")), new Char('"'))
		};
	}

	public static IStructure[] CaseInsensitiveString() {
		return new IStructure[] {
			new Alternative(new Char('"'), new Char('"'), new PlusList(new Sort("EscapedStringCharacter")), new Char('"'), new Char('"'))
		};
	}

	public static IStructure[] Sort() {
		return new IStructure[] {
			new Alternative(new CharRange('A', 'Z'), new StarList(new CharRanges(new CharRange('A', 'Z'), new CharRange('a', 'z'), new CharRange('0', '9'))).withAfterFilters(new CharRangeFollowRestriction('A', 'Z'),new CharRangeFollowRestriction('a', 'z'), new CharRangeFollowRestriction('0', '9')))
		};
	}
}
