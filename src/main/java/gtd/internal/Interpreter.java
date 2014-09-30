package gtd.internal;

import gtd.ParseErrorException;
import gtd.Parser;
import gtd.generator.FromClassGenerator;
import gtd.generator.ParserStructure;
import gtd.grammar.SyntaxSpec;
import gtd.result.AbstractNode;

public class Interpreter {
	private final ParserStructure structure;

	public Interpreter() {
		super();

		this.structure = new FromClassGenerator(SyntaxSpec.class).generate();
	}

	public void execute(String input) {
		try {
			AbstractNode result = parse(input);
			System.out.println(result); // Temp
		} catch(ParseErrorException e) {
			System.out.println(e.getMessage()); // Temp
		}
	}

	public AbstractNode parse(String input) {
		Parser parser = new Parser(input.toCharArray(), structure);
		return parser.parse("Grammar");
	}
}
