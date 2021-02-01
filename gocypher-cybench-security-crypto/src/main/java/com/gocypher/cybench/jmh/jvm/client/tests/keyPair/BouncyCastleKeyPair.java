package com.gocypher.cybench.jmh.jvm.client.tests.keyPair;

import com.gocypher.cybench.core.annotation.BenchmarkMetaData;
import com.gocypher.cybench.core.annotation.BenchmarkTag;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;

@BenchmarkMetaData(key = "api", value = "BouncyCastle")
@BenchmarkMetaData(key = "libSymbolicName", value = "org.bouncycastle.bcprovider")
@BenchmarkMetaData(key = "libVersion", value = "1.58")
@BenchmarkMetaData(key = "libDescription", value = "Bouncy Castle Java cryptography APIs")
@BenchmarkMetaData(key = "libVendor", value = "BouncyCastle.org")
@BenchmarkMetaData(key = "isLibraryBenchmark", value = "true")
public class BouncyCastleKeyPair extends KeyPairGenerationBenchmarks {


    @Benchmark
    @BenchmarkMetaData(key = "benchTitle", value = "Generation of asymmetric key DSA (512)")
    @BenchmarkMetaData(key = "keyAlgorithm", value = "DSA")
    @BenchmarkMetaData(key = "keySize", value = "512")
    @BenchmarkTag(tag = "0be3c3ed-c7c0-4a11-a547-b550e3fb3972")
    public void generateDSA_BC_512(Blackhole bh) {
        bh.consume(generateKey("BC", "DSA", 512));
    }

    @Benchmark
    @BenchmarkMetaData(key = "benchTitle", value = "Generation of asymmetric key DSA (1024)")
    @BenchmarkMetaData(key = "keyAlgorithm", value = "DSA")
    @BenchmarkMetaData(key = "keySize", value = "1024")
    @BenchmarkTag(tag = "9370448d-92d3-4434-9734-620ff7a36794")
    public void generateDSA_BC_1024(Blackhole bh) {
        bh.consume(generateKey("BC", "DSA", 1024));
    }

    @Benchmark
    @BenchmarkMetaData(key = "benchTitle", value = "Generation of asymmetric key DSA (2048)")
    @BenchmarkMetaData(key = "keyAlgorithm", value = "DSA")
    @BenchmarkMetaData(key = "keySize", value = "2048")
    @BenchmarkTag(tag = "c14ba059-ade7-49ce-be89-e249332e173d")
    public void generateDSA_BC_2048(Blackhole bh) {
        bh.consume(generateKey("BC", "DSA", 2048));
    }

    @Benchmark
    @BenchmarkMetaData(key = "benchTitle", value = "Generation of asymmetric key EC (224)")
    @BenchmarkMetaData(key = "keyAlgorithm", value = "EC")
    @BenchmarkMetaData(key = "keySize", value = "224")
    @BenchmarkTag(tag = "43c0cf14-e8ec-4e9b-bbd5-3e24f64b2e76")
    public void generateEC_BC_224(Blackhole bh) {
        bh.consume(generateKey("BC", "EC", 224));
    }

    @Benchmark
    @BenchmarkMetaData(key = "benchTitle", value = "Generation of asymmetric key EC (521)")
    @BenchmarkMetaData(key = "keyAlgorithm", value = "EC")
    @BenchmarkMetaData(key = "keySize", value = "521")
    @BenchmarkTag(tag = "2cfe0800-e033-4648-9555-e98eaf620656")
    public void generateEC_BC_521(Blackhole bh) {
        bh.consume(generateKey("BC", "EC", 521));
    }


    @Benchmark
    @BenchmarkMetaData(key = "benchTitle", value = "Generation of asymmetric key RSA (4096)")
    @BenchmarkMetaData(key = "keyAlgorithm", value = "RSA")
    @BenchmarkMetaData(key = "keySize", value = "4096")
    @BenchmarkTag(tag = "e035cda8-ba8e-4204-ac9f-7ebb9b0a05f7")

    public void generateRSA_BC_4096(Blackhole bh) {
        bh.consume(generateKey("BC", "RSA", 4096));
    }

    @Benchmark
    @BenchmarkMetaData(key = "benchTitle", value = "Generation of asymmetric key RSA (1024)")
    @BenchmarkMetaData(key = "keyAlgorithm", value = "RSA")
    @BenchmarkMetaData(key = "keySize", value = "1024")
    @BenchmarkTag(tag = "c0d03af4-50c3-448e-9870-7730e29c1948")
    public void generateRSA_BC_1024(Blackhole bh) {
        bh.consume(generateKey("BC", "RSA", 1024));
    }

}
