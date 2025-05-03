package br.com.emersonmendes.hexagonal.architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "br.com.emersonmendes.hexagonal")
public class NamingConventionSuffixTest {

    @ArchTest
    public static final ArchRule shouldBeSuffixedMapper = classes()
        .that()
        .resideInAnyPackage(
         "..adapters.in.controller.mapper..",
            "..adapters.out.repository.mapper..",
            "..adapters.out.client.mapper.."
        ).should()
        .haveSimpleNameEndingWith("Mapper");

    @ArchTest
    public static final ArchRule shouldBeSuffixedController = classes()
        .that()
        .resideInAPackage("..adapters.in.controller")
        .should()
        .haveSimpleNameEndingWith("Controller");

    @ArchTest
    public static final ArchRule shouldBeSuffixedOutputAdapter = classes()
        .that()
        .resideInAPackage("..adapters.out")
        .should()
        .haveSimpleNameEndingWith("OutputAdapter");

    @ArchTest
    public static final ArchRule shouldBeSuffixedRepository = classes()
        .that()
        .resideInAPackage("..adapters.out.repository")
        .should()
        .haveSimpleNameEndingWith("Repository");

    //TODO: Add others

}
