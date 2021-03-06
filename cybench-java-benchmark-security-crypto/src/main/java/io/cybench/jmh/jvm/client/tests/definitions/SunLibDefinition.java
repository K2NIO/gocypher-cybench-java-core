package io.cybench.jmh.jvm.client.tests.definitions;

import com.gocypher.cybench.core.annotation.BenchmarkMetaData;

@BenchmarkMetaData(key = "api", value = "Sun")
@BenchmarkMetaData(key = "libSymbolicName", value = "SUN")
@BenchmarkMetaData(key = "libVersion", value = "-")
@BenchmarkMetaData(key = "libVendor", value = "-")
@BenchmarkMetaData(key = "libDescription", value = "The first JDK provider was named SUN, and contained two types of cryptographic services (MessageDigests, Signatures, SecureRandom number generators, KeyPairGenerators, KeyFactories, and so on).")
@BenchmarkMetaData(key = "libUrl", value = "https://docs.oracle.com/javase/8/docs/technotes/guides/security/SunProviders.html")
@BenchmarkMetaData(key = "isLibraryBenchmark", value = "false")
public interface SunLibDefinition {
}
