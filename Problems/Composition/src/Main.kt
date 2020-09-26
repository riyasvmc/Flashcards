class OperatingSystem(var name: String = "Windows")
class DualBoot(var primaryOs: OperatingSystem = OperatingSystem("Windows"), var secondaryOs: OperatingSystem = OperatingSystem("Linux"))