################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
C_SRCS += \
../Src/LoRaMac.c \
../Src/LoRaMacCrypto.c \
../Src/aes.c \
../Src/board.c \
../Src/cmac.c \
../Src/delay.c \
../Src/fifo.c \
../Src/gpio-board.c \
../Src/gpio.c \
../Src/main.c \
../Src/rtc-board.c \
../Src/spi-board.c \
../Src/stm32l1xx_hal_msp.c \
../Src/stm32l1xx_it.c \
../Src/sx1276-board.c \
../Src/sx1276.c \
../Src/sysIrqHandlers.c \
../Src/syscalls.c \
../Src/sysmem.c \
../Src/system_stm32l1xx.c \
../Src/timer.c \
../Src/uart-board.c \
../Src/uart.c \
../Src/usb_device.c \
../Src/usbd_cdc_if.c \
../Src/usbd_conf.c \
../Src/usbd_desc.c \
../Src/utilities.c 

OBJS += \
./Src/LoRaMac.o \
./Src/LoRaMacCrypto.o \
./Src/aes.o \
./Src/board.o \
./Src/cmac.o \
./Src/delay.o \
./Src/fifo.o \
./Src/gpio-board.o \
./Src/gpio.o \
./Src/main.o \
./Src/rtc-board.o \
./Src/spi-board.o \
./Src/stm32l1xx_hal_msp.o \
./Src/stm32l1xx_it.o \
./Src/sx1276-board.o \
./Src/sx1276.o \
./Src/sysIrqHandlers.o \
./Src/syscalls.o \
./Src/sysmem.o \
./Src/system_stm32l1xx.o \
./Src/timer.o \
./Src/uart-board.o \
./Src/uart.o \
./Src/usb_device.o \
./Src/usbd_cdc_if.o \
./Src/usbd_conf.o \
./Src/usbd_desc.o \
./Src/utilities.o 

C_DEPS += \
./Src/LoRaMac.d \
./Src/LoRaMacCrypto.d \
./Src/aes.d \
./Src/board.d \
./Src/cmac.d \
./Src/delay.d \
./Src/fifo.d \
./Src/gpio-board.d \
./Src/gpio.d \
./Src/main.d \
./Src/rtc-board.d \
./Src/spi-board.d \
./Src/stm32l1xx_hal_msp.d \
./Src/stm32l1xx_it.d \
./Src/sx1276-board.d \
./Src/sx1276.d \
./Src/sysIrqHandlers.d \
./Src/syscalls.d \
./Src/sysmem.d \
./Src/system_stm32l1xx.d \
./Src/timer.d \
./Src/uart-board.d \
./Src/uart.d \
./Src/usb_device.d \
./Src/usbd_cdc_if.d \
./Src/usbd_conf.d \
./Src/usbd_desc.d \
./Src/utilities.d 


# Each subdirectory must supply rules for building sources it contributes
Src/LoRaMac.o: ../Src/LoRaMac.c
	arm-none-eabi-gcc "$<" -mcpu=cortex-m3 -std=gnu11 -DUSE_HAL_DRIVER -DSTM32L151xC -c -I../Inc -I../Drivers/CMSIS/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc -I../Drivers/CMSIS/Device/ST/STM32L1xx/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc/Legacy -O3 -ffunction-sections -fdata-sections -Wall -fstack-usage -MMD -MP -MF"Src/LoRaMac.d" -MT"$@" --specs=nano.specs -mfloat-abi=soft -mthumb -o "$@"
Src/LoRaMacCrypto.o: ../Src/LoRaMacCrypto.c
	arm-none-eabi-gcc "$<" -mcpu=cortex-m3 -std=gnu11 -DUSE_HAL_DRIVER -DSTM32L151xC -c -I../Inc -I../Drivers/CMSIS/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc -I../Drivers/CMSIS/Device/ST/STM32L1xx/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc/Legacy -O3 -ffunction-sections -fdata-sections -Wall -fstack-usage -MMD -MP -MF"Src/LoRaMacCrypto.d" -MT"$@" --specs=nano.specs -mfloat-abi=soft -mthumb -o "$@"
Src/aes.o: ../Src/aes.c
	arm-none-eabi-gcc "$<" -mcpu=cortex-m3 -std=gnu11 -DUSE_HAL_DRIVER -DSTM32L151xC -c -I../Inc -I../Drivers/CMSIS/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc -I../Drivers/CMSIS/Device/ST/STM32L1xx/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc/Legacy -O3 -ffunction-sections -fdata-sections -Wall -fstack-usage -MMD -MP -MF"Src/aes.d" -MT"$@" --specs=nano.specs -mfloat-abi=soft -mthumb -o "$@"
Src/board.o: ../Src/board.c
	arm-none-eabi-gcc "$<" -mcpu=cortex-m3 -std=gnu11 -DUSE_HAL_DRIVER -DSTM32L151xC -c -I../Inc -I../Drivers/CMSIS/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc -I../Drivers/CMSIS/Device/ST/STM32L1xx/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc/Legacy -O3 -ffunction-sections -fdata-sections -Wall -fstack-usage -MMD -MP -MF"Src/board.d" -MT"$@" --specs=nano.specs -mfloat-abi=soft -mthumb -o "$@"
Src/cmac.o: ../Src/cmac.c
	arm-none-eabi-gcc "$<" -mcpu=cortex-m3 -std=gnu11 -DUSE_HAL_DRIVER -DSTM32L151xC -c -I../Inc -I../Drivers/CMSIS/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc -I../Drivers/CMSIS/Device/ST/STM32L1xx/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc/Legacy -O3 -ffunction-sections -fdata-sections -Wall -fstack-usage -MMD -MP -MF"Src/cmac.d" -MT"$@" --specs=nano.specs -mfloat-abi=soft -mthumb -o "$@"
Src/delay.o: ../Src/delay.c
	arm-none-eabi-gcc "$<" -mcpu=cortex-m3 -std=gnu11 -DUSE_HAL_DRIVER -DSTM32L151xC -c -I../Inc -I../Drivers/CMSIS/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc -I../Drivers/CMSIS/Device/ST/STM32L1xx/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc/Legacy -O3 -ffunction-sections -fdata-sections -Wall -fstack-usage -MMD -MP -MF"Src/delay.d" -MT"$@" --specs=nano.specs -mfloat-abi=soft -mthumb -o "$@"
Src/fifo.o: ../Src/fifo.c
	arm-none-eabi-gcc "$<" -mcpu=cortex-m3 -std=gnu11 -DUSE_HAL_DRIVER -DSTM32L151xC -c -I../Inc -I../Drivers/CMSIS/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc -I../Drivers/CMSIS/Device/ST/STM32L1xx/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc/Legacy -O3 -ffunction-sections -fdata-sections -Wall -fstack-usage -MMD -MP -MF"Src/fifo.d" -MT"$@" --specs=nano.specs -mfloat-abi=soft -mthumb -o "$@"
Src/gpio-board.o: ../Src/gpio-board.c
	arm-none-eabi-gcc "$<" -mcpu=cortex-m3 -std=gnu11 -DUSE_HAL_DRIVER -DSTM32L151xC -c -I../Inc -I../Drivers/CMSIS/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc -I../Drivers/CMSIS/Device/ST/STM32L1xx/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc/Legacy -O3 -ffunction-sections -fdata-sections -Wall -fstack-usage -MMD -MP -MF"Src/gpio-board.d" -MT"$@" --specs=nano.specs -mfloat-abi=soft -mthumb -o "$@"
Src/gpio.o: ../Src/gpio.c
	arm-none-eabi-gcc "$<" -mcpu=cortex-m3 -std=gnu11 -DUSE_HAL_DRIVER -DSTM32L151xC -c -I../Inc -I../Drivers/CMSIS/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc -I../Drivers/CMSIS/Device/ST/STM32L1xx/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc/Legacy -O3 -ffunction-sections -fdata-sections -Wall -fstack-usage -MMD -MP -MF"Src/gpio.d" -MT"$@" --specs=nano.specs -mfloat-abi=soft -mthumb -o "$@"
Src/main.o: ../Src/main.c
	arm-none-eabi-gcc "$<" -mcpu=cortex-m3 -std=gnu11 -DUSE_HAL_DRIVER -DSTM32L151xC -c -I../Inc -I../Drivers/CMSIS/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc -I../Drivers/CMSIS/Device/ST/STM32L1xx/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc/Legacy -O3 -ffunction-sections -fdata-sections -Wall -fstack-usage -MMD -MP -MF"Src/main.d" -MT"$@" --specs=nano.specs -mfloat-abi=soft -mthumb -o "$@"
Src/rtc-board.o: ../Src/rtc-board.c
	arm-none-eabi-gcc "$<" -mcpu=cortex-m3 -std=gnu11 -DUSE_HAL_DRIVER -DSTM32L151xC -c -I../Inc -I../Drivers/CMSIS/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc -I../Drivers/CMSIS/Device/ST/STM32L1xx/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc/Legacy -O3 -ffunction-sections -fdata-sections -Wall -fstack-usage -MMD -MP -MF"Src/rtc-board.d" -MT"$@" --specs=nano.specs -mfloat-abi=soft -mthumb -o "$@"
Src/spi-board.o: ../Src/spi-board.c
	arm-none-eabi-gcc "$<" -mcpu=cortex-m3 -std=gnu11 -DUSE_HAL_DRIVER -DSTM32L151xC -c -I../Inc -I../Drivers/CMSIS/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc -I../Drivers/CMSIS/Device/ST/STM32L1xx/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc/Legacy -O3 -ffunction-sections -fdata-sections -Wall -fstack-usage -MMD -MP -MF"Src/spi-board.d" -MT"$@" --specs=nano.specs -mfloat-abi=soft -mthumb -o "$@"
Src/stm32l1xx_hal_msp.o: ../Src/stm32l1xx_hal_msp.c
	arm-none-eabi-gcc "$<" -mcpu=cortex-m3 -std=gnu11 -DUSE_HAL_DRIVER -DSTM32L151xC -c -I../Inc -I../Drivers/CMSIS/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc -I../Drivers/CMSIS/Device/ST/STM32L1xx/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc/Legacy -O3 -ffunction-sections -fdata-sections -Wall -fstack-usage -MMD -MP -MF"Src/stm32l1xx_hal_msp.d" -MT"$@" --specs=nano.specs -mfloat-abi=soft -mthumb -o "$@"
Src/stm32l1xx_it.o: ../Src/stm32l1xx_it.c
	arm-none-eabi-gcc "$<" -mcpu=cortex-m3 -std=gnu11 -DUSE_HAL_DRIVER -DSTM32L151xC -c -I../Inc -I../Drivers/CMSIS/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc -I../Drivers/CMSIS/Device/ST/STM32L1xx/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc/Legacy -O3 -ffunction-sections -fdata-sections -Wall -fstack-usage -MMD -MP -MF"Src/stm32l1xx_it.d" -MT"$@" --specs=nano.specs -mfloat-abi=soft -mthumb -o "$@"
Src/sx1276-board.o: ../Src/sx1276-board.c
	arm-none-eabi-gcc "$<" -mcpu=cortex-m3 -std=gnu11 -DUSE_HAL_DRIVER -DSTM32L151xC -c -I../Inc -I../Drivers/CMSIS/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc -I../Drivers/CMSIS/Device/ST/STM32L1xx/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc/Legacy -O3 -ffunction-sections -fdata-sections -Wall -fstack-usage -MMD -MP -MF"Src/sx1276-board.d" -MT"$@" --specs=nano.specs -mfloat-abi=soft -mthumb -o "$@"
Src/sx1276.o: ../Src/sx1276.c
	arm-none-eabi-gcc "$<" -mcpu=cortex-m3 -std=gnu11 -DUSE_HAL_DRIVER -DSTM32L151xC -c -I../Inc -I../Drivers/CMSIS/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc -I../Drivers/CMSIS/Device/ST/STM32L1xx/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc/Legacy -O3 -ffunction-sections -fdata-sections -Wall -fstack-usage -MMD -MP -MF"Src/sx1276.d" -MT"$@" --specs=nano.specs -mfloat-abi=soft -mthumb -o "$@"
Src/sysIrqHandlers.o: ../Src/sysIrqHandlers.c
	arm-none-eabi-gcc "$<" -mcpu=cortex-m3 -std=gnu11 -DUSE_HAL_DRIVER -DSTM32L151xC -c -I../Inc -I../Drivers/CMSIS/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc -I../Drivers/CMSIS/Device/ST/STM32L1xx/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc/Legacy -O3 -ffunction-sections -fdata-sections -Wall -fstack-usage -MMD -MP -MF"Src/sysIrqHandlers.d" -MT"$@" --specs=nano.specs -mfloat-abi=soft -mthumb -o "$@"
Src/syscalls.o: ../Src/syscalls.c
	arm-none-eabi-gcc "$<" -mcpu=cortex-m3 -std=gnu11 -DUSE_HAL_DRIVER -DSTM32L151xC -c -I../Inc -I../Drivers/CMSIS/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc -I../Drivers/CMSIS/Device/ST/STM32L1xx/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc/Legacy -O3 -ffunction-sections -fdata-sections -Wall -fstack-usage -MMD -MP -MF"Src/syscalls.d" -MT"$@" --specs=nano.specs -mfloat-abi=soft -mthumb -o "$@"
Src/sysmem.o: ../Src/sysmem.c
	arm-none-eabi-gcc "$<" -mcpu=cortex-m3 -std=gnu11 -DUSE_HAL_DRIVER -DSTM32L151xC -c -I../Inc -I../Drivers/CMSIS/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc -I../Drivers/CMSIS/Device/ST/STM32L1xx/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc/Legacy -O3 -ffunction-sections -fdata-sections -Wall -fstack-usage -MMD -MP -MF"Src/sysmem.d" -MT"$@" --specs=nano.specs -mfloat-abi=soft -mthumb -o "$@"
Src/system_stm32l1xx.o: ../Src/system_stm32l1xx.c
	arm-none-eabi-gcc "$<" -mcpu=cortex-m3 -std=gnu11 -DUSE_HAL_DRIVER -DSTM32L151xC -c -I../Inc -I../Drivers/CMSIS/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc -I../Drivers/CMSIS/Device/ST/STM32L1xx/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc/Legacy -O3 -ffunction-sections -fdata-sections -Wall -fstack-usage -MMD -MP -MF"Src/system_stm32l1xx.d" -MT"$@" --specs=nano.specs -mfloat-abi=soft -mthumb -o "$@"
Src/timer.o: ../Src/timer.c
	arm-none-eabi-gcc "$<" -mcpu=cortex-m3 -std=gnu11 -DUSE_HAL_DRIVER -DSTM32L151xC -c -I../Inc -I../Drivers/CMSIS/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc -I../Drivers/CMSIS/Device/ST/STM32L1xx/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc/Legacy -O3 -ffunction-sections -fdata-sections -Wall -fstack-usage -MMD -MP -MF"Src/timer.d" -MT"$@" --specs=nano.specs -mfloat-abi=soft -mthumb -o "$@"
Src/uart-board.o: ../Src/uart-board.c
	arm-none-eabi-gcc "$<" -mcpu=cortex-m3 -std=gnu11 -DUSE_HAL_DRIVER -DSTM32L151xC -c -I../Inc -I../Drivers/CMSIS/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc -I../Drivers/CMSIS/Device/ST/STM32L1xx/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc/Legacy -O3 -ffunction-sections -fdata-sections -Wall -fstack-usage -MMD -MP -MF"Src/uart-board.d" -MT"$@" --specs=nano.specs -mfloat-abi=soft -mthumb -o "$@"
Src/uart.o: ../Src/uart.c
	arm-none-eabi-gcc "$<" -mcpu=cortex-m3 -std=gnu11 -DUSE_HAL_DRIVER -DSTM32L151xC -c -I../Inc -I../Drivers/CMSIS/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc -I../Drivers/CMSIS/Device/ST/STM32L1xx/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc/Legacy -O3 -ffunction-sections -fdata-sections -Wall -fstack-usage -MMD -MP -MF"Src/uart.d" -MT"$@" --specs=nano.specs -mfloat-abi=soft -mthumb -o "$@"
Src/usb_device.o: ../Src/usb_device.c
	arm-none-eabi-gcc "$<" -mcpu=cortex-m3 -std=gnu11 -DUSE_HAL_DRIVER -DSTM32L151xC -c -I../Inc -I../Drivers/CMSIS/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc -I../Drivers/CMSIS/Device/ST/STM32L1xx/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc/Legacy -O3 -ffunction-sections -fdata-sections -Wall -fstack-usage -MMD -MP -MF"Src/usb_device.d" -MT"$@" --specs=nano.specs -mfloat-abi=soft -mthumb -o "$@"
Src/usbd_cdc_if.o: ../Src/usbd_cdc_if.c
	arm-none-eabi-gcc "$<" -mcpu=cortex-m3 -std=gnu11 -DUSE_HAL_DRIVER -DSTM32L151xC -c -I../Inc -I../Drivers/CMSIS/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc -I../Drivers/CMSIS/Device/ST/STM32L1xx/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc/Legacy -O3 -ffunction-sections -fdata-sections -Wall -fstack-usage -MMD -MP -MF"Src/usbd_cdc_if.d" -MT"$@" --specs=nano.specs -mfloat-abi=soft -mthumb -o "$@"
Src/usbd_conf.o: ../Src/usbd_conf.c
	arm-none-eabi-gcc "$<" -mcpu=cortex-m3 -std=gnu11 -DUSE_HAL_DRIVER -DSTM32L151xC -c -I../Inc -I../Drivers/CMSIS/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc -I../Drivers/CMSIS/Device/ST/STM32L1xx/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc/Legacy -O3 -ffunction-sections -fdata-sections -Wall -fstack-usage -MMD -MP -MF"Src/usbd_conf.d" -MT"$@" --specs=nano.specs -mfloat-abi=soft -mthumb -o "$@"
Src/usbd_desc.o: ../Src/usbd_desc.c
	arm-none-eabi-gcc "$<" -mcpu=cortex-m3 -std=gnu11 -DUSE_HAL_DRIVER -DSTM32L151xC -c -I../Inc -I../Drivers/CMSIS/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc -I../Drivers/CMSIS/Device/ST/STM32L1xx/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc/Legacy -O3 -ffunction-sections -fdata-sections -Wall -fstack-usage -MMD -MP -MF"Src/usbd_desc.d" -MT"$@" --specs=nano.specs -mfloat-abi=soft -mthumb -o "$@"
Src/utilities.o: ../Src/utilities.c
	arm-none-eabi-gcc "$<" -mcpu=cortex-m3 -std=gnu11 -DUSE_HAL_DRIVER -DSTM32L151xC -c -I../Inc -I../Drivers/CMSIS/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc -I../Drivers/CMSIS/Device/ST/STM32L1xx/Include -I../Drivers/STM32L1xx_HAL_Driver/Inc/Legacy -O3 -ffunction-sections -fdata-sections -Wall -fstack-usage -MMD -MP -MF"Src/utilities.d" -MT"$@" --specs=nano.specs -mfloat-abi=soft -mthumb -o "$@"

