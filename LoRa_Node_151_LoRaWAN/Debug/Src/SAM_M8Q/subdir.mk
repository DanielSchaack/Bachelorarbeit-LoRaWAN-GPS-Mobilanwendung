################################################################################
# Automatically-generated file. Do not edit!
# Toolchain: GNU Tools for STM32 (9-2020-q2-update)
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
C_SRCS += \
../Src/SAM_M8Q/SAM_M8Q.c 

OBJS += \
./Src/SAM_M8Q/SAM_M8Q.o 

C_DEPS += \
./Src/SAM_M8Q/SAM_M8Q.d 


# Each subdirectory must supply rules for building sources it contributes
Src/SAM_M8Q/SAM_M8Q.o: ../Src/SAM_M8Q/SAM_M8Q.c Src/SAM_M8Q/subdir.mk
	arm-none-eabi-gcc "$<" -mcpu=cortex-m3 -std=gnu11 -g3 -DUSE_HAL_DRIVER -DSTM32L151xC '-DACTIVE_REGION=LORAMAC_REGION_EU868' '-DACTIVE_CLASS=CLASS_A' -DREGION_EU868 -DDEBUG -c -I../Inc -I../Drivers/CMSIS/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc -I../Drivers/CMSIS/Device/ST/STM32L1xx/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc/Legacy -I../Inc/region -O0 -ffunction-sections -fdata-sections -Wall -fstack-usage -MMD -MP -MF"Src/SAM_M8Q/SAM_M8Q.d" -MT"$@" --specs=nano.specs -mfloat-abi=soft -mthumb -o "$@"

