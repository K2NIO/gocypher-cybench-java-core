/*
 * Copyright (C) 2020, K2N.IO.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 *
 */
package com.gocypher.cybench.jmh.jvm.client.tests;

import com.gocypher.cybench.jmh.jvm.utils.IOUtils;
import org.openjdk.jmh.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;
import java.util.concurrent.TimeUnit;
import com.gocypher.cybench.core.annotation.BenchmarkTag;

@State(Scope.Benchmark)
public class IOAsyncAPIComparisonBenchmarks {

    private static Logger LOG = LoggerFactory.getLogger(IOAsyncAPIComparisonBenchmarks.class);

    private File srcFile;

    private File targetFile;

    private long fileSize = 0;

    private FileChannel readFileChannel;

    private FileChannel writeFileChannel;

    private int smallChunk = 4_096;

    private int hugeChunk = 67_108_864;

    private boolean isSyncWrite = false;

    @Setup(Level.Trial)
    public void setupForFork() throws Exception {
        LOG.info("\n-->Will generate binary file for tests...");
        srcFile = IOUtils.generateBinaryFileForTests();
        fileSize = srcFile.length();
        LOG.info("\n-->Generated file {} for processing, size(B):{}", srcFile, fileSize);
        readFileChannel = (FileChannel) Files.newByteChannel(srcFile.toPath(), EnumSet.of(StandardOpenOption.READ));
    }

    @Setup(Level.Iteration)
    public void setupForIteration() throws Exception {
        LOG.info("\n Will setup for iteration...");
        targetFile = IOUtils.createOutputFileForTests();
        writeFileChannel = (FileChannel) Files.newByteChannel(targetFile.toPath(), EnumSet.of(StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING));
    }

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkTag(tag = "f569ac52-072d-4bcc-a896-843b739c4f2b")
    public void copyFileUsingMappedByteBuffer() throws Exception {
        IOUtils.rwFileUsingMappedByteBuffer(readFileChannel, writeFileChannel, isSyncWrite);
    }

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkTag(tag = "26be83d9-e801-4fee-a8df-b282bf730382")
    public void copyFileUsingFileStreamAndSmallChunks() throws Exception {
        long bytesCopied = IOUtils.copyFileUsingFileStreams(srcFile, targetFile, smallChunk, isSyncWrite);
        assert bytesCopied == fileSize;
    }

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkTag(tag = "790d6d8b-81bb-4599-b0dd-0aed5ed9127f")
    public void copyFileUsingFileStreamAndHugeChunks() throws Exception {
        long bytesCopied = IOUtils.copyFileUsingFileStreams(srcFile, targetFile, hugeChunk, isSyncWrite);
        assert bytesCopied == fileSize;
    }

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkTag(tag = "baa29159-7bbf-48e1-9fb9-a9fb05943499")
    public void copyFileUsingBufferedStreamAndSmallChunks() throws Exception {
        long bytesCopied = IOUtils.copyFileUsingBufferedStreams(srcFile, targetFile, smallChunk, isSyncWrite);
        assert bytesCopied == fileSize;
    }

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkTag(tag = "4be92515-66e9-4255-a7f5-c262e4362f46")
    public void copyFileUsingBufferedStreamAndHugeChunks() throws Exception {
        long bytesCopied = IOUtils.copyFileUsingBufferedStreams(srcFile, targetFile, hugeChunk, isSyncWrite);
        assert bytesCopied == fileSize;
    }

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkTag(tag = "7ebb2f8e-eb1e-4cd6-ac9b-f8e2270a5358")
    public void copyFileUsingDirectBufferedStreamAndSmallChunks() throws Exception {
        long bytesCopied = IOUtils.copyFileUsingDirectBufferedStreams(srcFile, targetFile, smallChunk, isSyncWrite);
        assert bytesCopied == fileSize;
    }

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkTag(tag = "76eb336e-c80e-48eb-99c4-3b27f9b6c56f")
    public void copyFileUsingDirectBufferedStreamAndHugeChunks() throws Exception {
        long bytesCopied = IOUtils.copyFileUsingDirectBufferedStreams(srcFile, targetFile, hugeChunk, isSyncWrite);
        assert bytesCopied == fileSize;
    }

    @TearDown(Level.Iteration)
    public void cleanUpAfterIteration() {
        try {
            if (writeFileChannel != null) {
                writeFileChannel.close();
            }
            IOUtils.removeFile(targetFile);
            LOG.info("\n==>Iteration clean up successful!");
        } catch (Exception e) {
            LOG.error("Error on file removal", e);
        }
    }

    @TearDown(Level.Trial)
    public void cleanUpAfterFork() {
        try {
            if (readFileChannel != null) {
                readFileChannel.close();
            }
            // IOUtils.removeFile(srcFile);
            LOG.info("\n==>Generated files were closed successfully!");
        } catch (Exception e) {
            LOG.error("Error on file removal", e);
        }
    }
}
