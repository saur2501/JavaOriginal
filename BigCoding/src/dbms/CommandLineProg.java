package dbms;

import org.apache.commons.cli.CommandLine;
import com.google.common.base.Preconditions;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class CommandLineProg {
	public static void main(String[] parameters)
    {
        CommandLine commandLine;
        Option option_A = OptionBuilder.withArgName("opt3").hasArg().withDescription("The A option").create("A");
        Option option_r = OptionBuilder.withArgName("opt1").hasArg().withDescription("The r option").create("r");
        Option option_S = OptionBuilder.withArgName("opt2").hasArg().withDescription("The S option").create("S");
        Option option_test = new Option("test", "The test option");
        Options options = new Options();
        CommandLineParser parser = new GnuParser();

        String[] testArgs =
        { "-r", "opt1", "-S", "opt2", "arg1", "arg2",
          "arg3", "arg4", "--test", "-A", "opt3", };

        options.addOption(option_A);
        options.addOption(option_r);
        options.addOption(option_S);
        options.addOption(option_test);
        try
        {
            commandLine = parser.parse(options, testArgs);
            Preconditions.checkArgument(commandLine.hasOption("A"), "Option not supplied");
            if (commandLine.hasOption("A"))
            {
                System.out.print("Option A is present.  The value is: ");
                System.out.println(commandLine.getOptionValue("A"));
            }

            if (commandLine.hasOption("r"))
            {
                System.out.print("Option r is present.  The value is: ");
                System.out.println(commandLine.getOptionValue("r"));
            }

            if (commandLine.hasOption("S"))
            {
                System.out.print("Option S is present.  The value is: ");
                System.out.println(commandLine.getOptionValue("S"));
            }

            if (commandLine.hasOption("test"))
            {
                System.out.println("Option test is present.  This is a flag option.");
            }

            {
                String[] remainder = commandLine.getArgs();
                System.out.print("Remaining arguments: ");
                for (String argument : remainder)
                {
                    System.out.print(argument);
                    System.out.print(" ");
                }

                System.out.println();
            }

        }
        catch (ParseException exception)
        {
            System.out.print("Parse error: ");
            System.out.println(exception.getMessage());
        }
    }
}
