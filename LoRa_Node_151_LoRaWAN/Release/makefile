################################################################################
# Automatically-generated file. Do not edit!
################################################################################

-include ../makefile.init

RM := rm -rf

# All of the sources participating in the build are defined here
-include sources.mk
-include Startup/subdir.mk
-include Src/subdir.mk
-include Drivers/STM32L1xx_HAL_Driver/Src/subdir.mk
-include subdir.mk
-include objects.mk

ifneq ($(MAKECMDGOALS),clean)
ifneq ($(strip $(C_DEPS)),)
-include $(C_DEPS)
endif
endif

-include ../makefile.defs

# Add inputs and outputs from these tool invocations to the build variables 
EXECUTABLES += \
LoRaWAN_151CC.elf \

SIZE_OUTPUT += \
default.size.stdout \

OBJDUMP_LIST += \
LoRaWAN_151CC.list \


# All Target
all: LoRaWAN_151CC.elf secondary-outputs

# Tool invocations
LoRaWAN_151CC.elf: $(OBJS) $(USER_OBJS) E:\DreamSpark\LoRaStuffs\Nodes\LoRa_Node_151\Code\LoRaWAN_151CC\STM32L151CCUX_FLASH.ld
	arm-none-eabi-gcc -o "LoRaWAN_151CC.elf" @"objects.list" $(USER_OBJS) $(LIBS) -mcpu=cortex-m3 -T"E:\DreamSpark\LoRaStuffs\Nodes\LoRa_Node_151\Code\LoRaWAN_151CC\STM32L151CCUX_FLASH.ld" --specs=nosys.specs -Wl,-Map="LoRaWAN_151CC.map" -Wl,--gc-sections -static --specs=nano.specs -mfloat-abi=soft -mthumb -Wl,--start-group -lc -lm -Wl,--end-group
	@echo 'Finished building target: $@'
	@echo ' '

default.size.stdout: $(EXECUTABLES)
	arm-none-eabi-size  $(EXECUTABLES)
	@echo 'Finished building: $@'
	@echo ' '

LoRaWAN_151CC.list: $(EXECUTABLES)
	arm-none-eabi-objdump -h -S $(EXECUTABLES) > "LoRaWAN_151CC.list"
	@echo 'Finished building: $@'
	@echo ' '

# Other Targets
clean:
	-$(RM) *
	-@echo ' '

secondary-outputs: $(SIZE_OUTPUT) $(OBJDUMP_LIST)

.PHONY: all clean dependents
.SECONDARY:

-include ../makefile.targets
