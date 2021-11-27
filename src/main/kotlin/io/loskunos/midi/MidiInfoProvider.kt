package io.loskunos.midi

import javax.sound.midi.MidiDevice
import javax.sound.midi.MidiSystem


object MidiInfoProvider {
    data class MidiDeviceInfo(
        val id: Int,
        val type: String,
        val name: String,
        val description: String,
        val vendor: String,
        val version: String,
    )

    fun getAvailableMidiDevicesInfo(): List<MidiDeviceInfo> =
        MidiSystem.getMidiDeviceInfo()
            .map {
                MidiSystem.getMidiDevice(it)
            }
            .mapIndexed { index, it ->
                MidiDeviceInfo(
                    id = index,
                    type = it.javaClass.simpleName,
                    name = it.deviceInfo.name,
                    description = it.deviceInfo.description,
                    vendor = it.deviceInfo.vendor,
                    version = it.deviceInfo.version,
                )
            }

    fun midiDeviceFrom(info: MidiDeviceInfo): MidiDevice? =
        MidiSystem.getMidiDeviceInfo()
            .singleOrNull {
                it.isRepresenting(info)
            }.let {
                MidiSystem.getMidiDevice(it)
            }


    private fun MidiDevice.Info.isRepresenting(info: MidiDeviceInfo): Boolean =
        this.name.equals(info.name)
                && this.vendor.equals(info.vendor)
                && this.version.equals(version)
                && this.description.equals(info.description)
}

