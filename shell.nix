with import <nixpkgs> {};
mkShell.override { stdenv = llvmPackages_21.stdenv; } {
    buildInputs = [
        openjdk25
        shellcheck
    ];
    shellHook = ''
        . .shellhook
    '';
}
