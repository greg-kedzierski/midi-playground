package io.loskunos.midi

import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import org.junit.jupiter.api.Test
import javax.sound.midi.MidiDevice
import javax.sound.midi.MidiSystem

internal class MidiInfoProviderTest {
    @Test
    fun `## should be very happy`() {
        mockkStatic(MidiSystem::class)
        every { MidiSystem.getMidiDeviceInfo() } returns
                arrayOf(
                    mockMidiDeviceInfo("dev#1"),
                    mockMidiDeviceInfo("dev#2")
                )
        every { MidiSystem.getMidiDevice(any()) } answers {
            mockk {
                every { deviceInfo } returns firstArg()
            }
        }


        val availableMidiDevicesInfo = MidiInfoProvider.getAvailableMidiDevicesInfo()
        print(availableMidiDevicesInfo)
    }

    private fun mockMidiDeviceInfo(
        name: String,
        vendor: String = "vendor",
        description: String = "description",
        version: String = "version"
    ): MidiDevice.Info =
        mockk<MidiDevice.Info>()
            .also {
                every { it.name } returns name
                every { it.vendor } returns vendor
                every { it.description } returns description
                every { it.version } returns version
            }
}