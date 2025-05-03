package br.com.emersonmendes.hexagonal.arch;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.Architectures;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "br.com.emersonmendes.hexagonal")
public class NamingConventionTest {

    @ArchTest
    public static final ArchRule mapperResideOnlyMapperPackage = classes()
        .that()
        .haveNameMatching(".*Mapper")
            .should()
            .resideInAnyPackage(
             "..adapters.in.controller.mapper..",
                "..adapters.out.repository.mapper..",
                "..adapters.out.client.mapper.."
            );

    @ArchTest
    public static final ArchRule controllerResideOnlyControllerPackage = classes()
        .that()
        .haveNameMatching(".*Controller")
        .should()
        .resideInAPackage("..adapters.in.controller..");

    @ArchTest
    public static final ArchRule adapterResideOnlyAdapterPackage = classes()
        .that()
        .haveNameMatching(".*Adapter")
        .should()
        .resideInAPackage("..hexagonal.adapters.out..");

    //TODO: Add others


}
