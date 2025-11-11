/* =======================
   C++ SECTION
   ======================= */
#ifndef BUILD_AS_C
#include <iostream>

// Declare the C function with C linkage so C++ can link with it
extern "C" void cfunc();

int main() {
    std::cout << "Calling C code..." << std::endl;
    cfunc();
    return 0;
}

#endif // BUILD_AS_C

/* =======================
   C SECTION
   ======================= */
#ifdef BUILD_AS_C
#include <stdio.h>

/*

Why the below code is invalid in C++:
Variable Length Arrays (VLA) are a C99 feature; standard C++ does not support VLAs.
Compound literals like (int[]){1,2,3} are allowed in C99 but not in C++.

*/

void cfunc() {
    main_();
    printf("%d\n", main_());
}

int main_(){
    // Variable Length Array (VLA) - valid in C99, invalid in standard C++
    int n = 5;
    int arr[n];  // <-- invalid in C++

    for (int i = 0; i < n; i++) {
        arr[i] = i * 2;
    }

    // Use a C99-style compound literal - invalid in C++
    int *p = (int[]){1, 2, 3, 4, 5};

    printf("VLA contents: ");
    for (int i = 0; i < n; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n");

    printf("Compound literal first element: %d\n", p[0]);

    nested_function();

    return 0;
}

void nested_function(){
    printf("Hello from nested function!");
}

#endif
