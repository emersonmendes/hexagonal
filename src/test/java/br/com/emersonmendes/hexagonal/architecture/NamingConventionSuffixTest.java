package br.com.emersonmendes.hexagonal.architecture;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(
    packages = "br.com.emersonmendes.hexagonal",
    importOptions = ImportOption.DoNotIncludeTests.class
)
public class NamingConventionSuffixTest {

    @ArchTest
    public static final ArchRule shouldBeSuffixedMapper = classes()
        .that()
        .resideInAnyPackage(
         "..adapter.in.controller.mapper..",
            "..adapter.out.repository.mapper..",
            "..adapter.out.client.mapper.."
        ).should()
        .haveSimpleNameEndingWith("Mapper");

    @ArchTest
    public static final ArchRule shouldBeSuffixedController = classes()
        .that()
        .resideInAPackage("..adapter.in.web")
        .should()
        .haveSimpleNameEndingWith("Controller");

    @ArchTest
    public static final ArchRule shouldBeSuffixedConsumer = classes()
            .that()
            .resideInAPackage("..adapter.in.messaging")
            .should()
            .haveSimpleNameEndingWith("Consumer");

    @ArchTest
    public static final ArchRule shouldBeSuffixedOutputAdapter = classes()
        .that()
        .resideInAPackage("..adapter.out")
        .should()
        .haveSimpleNameEndingWith("OutputAdapter");

    @ArchTest
    public static final ArchRule shouldBeSuffixedRepository = classes()
        .that()
        .resideInAPackage("..adapter.out.repository")
        .should()
        .haveSimpleNameEndingWith("Repository");

    //TODO: Add others

}
