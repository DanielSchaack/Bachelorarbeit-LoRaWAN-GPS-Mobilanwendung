################################################################################
# Automatically-generated file. Do not edit!
# Toolchain: GNU Tools for STM32 (9-2020-q2-update)
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
C_SRCS += \
../Src/region/RegionAS923.c \
../Src/region/RegionAU915.c \
../Src/region/RegionCN470.c \
../Src/region/RegionCN779.c \
../Src/region/RegionEU433.c \
../Src/region/RegionEU868.c \
../Src/region/RegionIN865.c \
../Src/region/RegionKR920.c \
../Src/region/RegionUS915-Hybrid.c \
../Src/region/RegionUS915.c 

OBJS += \
./Src/region/RegionAS923.o \
./Src/region/RegionAU915.o \
./Src/region/RegionCN470.o \
./Src/region/RegionCN779.o \
./Src/region/RegionEU433.o \
./Src/region/RegionEU868.o \
./Src/region/RegionIN865.o \
./Src/region/RegionKR920.o \
./Src/region/RegionUS915-Hybrid.o \
./Src/region/RegionUS915.o 

C_DEPS += \
./Src/region/RegionAS923.d \
./Src/region/RegionAU915.d \
./Src/region/RegionCN470.d \
./Src/region/RegionCN779.d \
./Src/region/RegionEU433.d \
./Src/region/RegionEU868.d \
./Src/region/RegionIN865.d \
./Src/region/RegionKR920.d \
./Src/region/RegionUS915-Hybrid.d \
./Src/region/RegionUS915.d 


# Each subdirectory must supply rules for building sources it contributes
Src/region/RegionAS923.o: ../Src/region/RegionAS923.c Src/region/subdir.mk
	arm-none-eabi-gcc "$<" -mcpu=cortex-m3 -std=gnu11 -g3 -DUSE_HAL_DRIVER -DSTM32L151xC '-DACTIVE_REGION=LORAMAC_REGION_EU868' '-DACTIVE_CLASS=CLASS_A' -DUSE_DEBUGGER -DREGION_EU868 -DDEBUG -c -I../Inc -I../Drivers/CMSIS/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc -I../Drivers/CMSIS/Device/ST/STM32L1xx/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc/Legacy -I../Inc/region -O0 -ffunction-sections -fdata-sections -Wall -fstack-usage -MMD -MP -MF"Src/region/RegionAS923.d" -MT"$@" --specs=nano.specs -mfloat-abi=soft -mthumb -o "$@"
Src/region/RegionAU915.o: ../Src/region/RegionAU915.c Src/region/subdir.mk
	arm-none-eabi-gcc "$<" -mcpu=cortex-m3 -std=gnu11 -g3 -DUSE_HAL_DRIVER -DSTM32L151xC '-DACTIVE_REGION=LORAMAC_REGION_EU868' '-DACTIVE_CLASS=CLASS_A' -DUSE_DEBUGGER -DREGION_EU868 -DDEBUG -c -I../Inc -I../Drivers/CMSIS/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc -I../Drivers/CMSIS/Device/ST/STM32L1xx/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc/Legacy -I../Inc/region -O0 -ffunction-sections -fdata-sections -Wall -fstack-usage -MMD -MP -MF"Src/region/RegionAU915.d" -MT"$@" --specs=nano.specs -mfloat-abi=soft -mthumb -o "$@"
Src/region/RegionCN470.o: ../Src/region/RegionCN470.c Src/region/subdir.mk
	arm-none-eabi-gcc "$<" -mcpu=cortex-m3 -std=gnu11 -g3 -DUSE_HAL_DRIVER -DSTM32L151xC '-DACTIVE_REGION=LORAMAC_REGION_EU868' '-DACTIVE_CLASS=CLASS_A' -DUSE_DEBUGGER -DREGION_EU868 -DDEBUG -c -I../Inc -I../Drivers/CMSIS/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc -I../Drivers/CMSIS/Device/ST/STM32L1xx/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc/Legacy -I../Inc/region -O0 -ffunction-sections -fdata-sections -Wall -fstack-usage -MMD -MP -MF"Src/region/RegionCN470.d" -MT"$@" --specs=nano.specs -mfloat-abi=soft -mthumb -o "$@"
Src/region/RegionCN779.o: ../Src/region/RegionCN779.c Src/region/subdir.mk
	arm-none-eabi-gcc "$<" -mcpu=cortex-m3 -std=gnu11 -g3 -DUSE_HAL_DRIVER -DSTM32L151xC '-DACTIVE_REGION=LORAMAC_REGION_EU868' '-DACTIVE_CLASS=CLASS_A' -DUSE_DEBUGGER -DREGION_EU868 -DDEBUG -c -I../Inc -I../Drivers/CMSIS/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc -I../Drivers/CMSIS/Device/ST/STM32L1xx/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc/Legacy -I../Inc/region -O0 -ffunction-sections -fdata-sections -Wall -fstack-usage -MMD -MP -MF"Src/region/RegionCN779.d" -MT"$@" --specs=nano.specs -mfloat-abi=soft -mthumb -o "$@"
Src/region/RegionEU433.o: ../Src/region/RegionEU433.c Src/region/subdir.mk
	arm-none-eabi-gcc "$<" -mcpu=cortex-m3 -std=gnu11 -g3 -DUSE_HAL_DRIVER -DSTM32L151xC '-DACTIVE_REGION=LORAMAC_REGION_EU868' '-DACTIVE_CLASS=CLASS_A' -DUSE_DEBUGGER -DREGION_EU868 -DDEBUG -c -I../Inc -I../Drivers/CMSIS/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc -I../Drivers/CMSIS/Device/ST/STM32L1xx/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc/Legacy -I../Inc/region -O0 -ffunction-sections -fdata-sections -Wall -fstack-usage -MMD -MP -MF"Src/region/RegionEU433.d" -MT"$@" --specs=nano.specs -mfloat-abi=soft -mthumb -o "$@"
Src/region/RegionEU868.o: ../Src/region/RegionEU868.c Src/region/subdir.mk
	arm-none-eabi-gcc "$<" -mcpu=cortex-m3 -std=gnu11 -g3 -DUSE_HAL_DRIVER -DSTM32L151xC '-DACTIVE_REGION=LORAMAC_REGION_EU868' '-DACTIVE_CLASS=CLASS_A' -DUSE_DEBUGGER -DREGION_EU868 -DDEBUG -c -I../Inc -I../Drivers/CMSIS/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc -I../Drivers/CMSIS/Device/ST/STM32L1xx/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc/Legacy -I../Inc/region -O0 -ffunction-sections -fdata-sections -Wall -fstack-usage -MMD -MP -MF"Src/region/RegionEU868.d" -MT"$@" --specs=nano.specs -mfloat-abi=soft -mthumb -o "$@"
Src/region/RegionIN865.o: ../Src/region/RegionIN865.c Src/region/subdir.mk
	arm-none-eabi-gcc "$<" -mcpu=cortex-m3 -std=gnu11 -g3 -DUSE_HAL_DRIVER -DSTM32L151xC '-DACTIVE_REGION=LORAMAC_REGION_EU868' '-DACTIVE_CLASS=CLASS_A' -DUSE_DEBUGGER -DREGION_EU868 -DDEBUG -c -I../Inc -I../Drivers/CMSIS/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc -I../Drivers/CMSIS/Device/ST/STM32L1xx/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc/Legacy -I../Inc/region -O0 -ffunction-sections -fdata-sections -Wall -fstack-usage -MMD -MP -MF"Src/region/RegionIN865.d" -MT"$@" --specs=nano.specs -mfloat-abi=soft -mthumb -o "$@"
Src/region/RegionKR920.o: ../Src/region/RegionKR920.c Src/region/subdir.mk
	arm-none-eabi-gcc "$<" -mcpu=cortex-m3 -std=gnu11 -g3 -DUSE_HAL_DRIVER -DSTM32L151xC '-DACTIVE_REGION=LORAMAC_REGION_EU868' '-DACTIVE_CLASS=CLASS_A' -DUSE_DEBUGGER -DREGION_EU868 -DDEBUG -c -I../Inc -I../Drivers/CMSIS/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc -I../Drivers/CMSIS/Device/ST/STM32L1xx/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc/Legacy -I../Inc/region -O0 -ffunction-sections -fdata-sections -Wall -fstack-usage -MMD -MP -MF"Src/region/RegionKR920.d" -MT"$@" --specs=nano.specs -mfloat-abi=soft -mthumb -o "$@"
Src/region/RegionUS915-Hybrid.o: ../Src/region/RegionUS915-Hybrid.c Src/region/subdir.mk
	arm-none-eabi-gcc "$<" -mcpu=cortex-m3 -std=gnu11 -g3 -DUSE_HAL_DRIVER -DSTM32L151xC '-DACTIVE_REGION=LORAMAC_REGION_EU868' '-DACTIVE_CLASS=CLASS_A' -DUSE_DEBUGGER -DREGION_EU868 -DDEBUG -c -I../Inc -I../Drivers/CMSIS/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc -I../Drivers/CMSIS/Device/ST/STM32L1xx/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc/Legacy -I../Inc/region -O0 -ffunction-sections -fdata-sections -Wall -fstack-usage -MMD -MP -MF"Src/region/RegionUS915-Hybrid.d" -MT"$@" --specs=nano.specs -mfloat-abi=soft -mthumb -o "$@"
Src/region/RegionUS915.o: ../Src/region/RegionUS915.c Src/region/subdir.mk
	arm-none-eabi-gcc "$<" -mcpu=cortex-m3 -std=gnu11 -g3 -DUSE_HAL_DRIVER -DSTM32L151xC '-DACTIVE_REGION=LORAMAC_REGION_EU868' '-DACTIVE_CLASS=CLASS_A' -DUSE_DEBUGGER -DREGION_EU868 -DDEBUG -c -I../Inc -I../Drivers/CMSIS/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc -I../Drivers/CMSIS/Device/ST/STM32L1xx/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc/Legacy -I../Inc/region -O0 -ffunction-sections -fdata-sections -Wall -fstack-usage -MMD -MP -MF"Src/region/RegionUS915.d" -MT"$@" --specs=nano.specs -mfloat-abi=soft -mthumb -o "$@"

