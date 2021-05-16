import os

JAR_NAME = 'semaphores-1.0.0.jar'

# Make sure to run `gradlew build` before using this script

def get_program_output():
    script_directory = os.path.dirname(os.path.realpath(__file__))
    jar_directory = os.path.abspath(os.path.join(script_directory,
        '../build/libs'))
    os.chdir(jar_directory)

    output_stream = os.popen(f'java -jar {JAR_NAME}')
    output = output_stream.read()

    return output

def check_output(output):
    w_count, x_count, y_count, z_count = 0, 0, 0, 0
    w_expected, y_expected = True, True

    try:
        for c in output:
            if c in ['W', 'X']:
                assert w_expected == (c == 'W')
                w_expected = not w_expected

            if c in ['y', 'z']:
                assert y_expected == (c == 'y')
                y_expected = not y_expected

            if c == 'W':
                w_count += 1
            elif c == 'X':
                x_count += 1
            elif c == 'y':
                y_count += 1
            elif c == 'z':
                z_count += 1
            assert w_count >= y_count + z_count

        print('Program output passed checks 🎉')
    except AssertionError:
        print('Program output failed checks 😔')

if __name__ == '__main__':
    program_output = get_program_output()
    print(f'Program output was:\n{program_output}')
    check_output(program_output)
