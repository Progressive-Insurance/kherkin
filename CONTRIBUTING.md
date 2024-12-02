# Contributing to Kherkin

We warmly welcome any community contributions to this repository. Contributions should follow [kotlin coding conventions](STEPS.md#kotlin), and new steps should follow the [step definition guidelines](STEPS.md#writing-new-steps).

## Code of Conduct

Help us ensure a welcoming and inspiring community. Please read our [Code of Conduct](./CODE_OF_CONDUCT.md).

## Found a bug?

If you've discovered a bug you can [submit an issue](https://github.com/progressive-insurance/kherkin/issues), or skip straight to [creating a pull request](#submitting-a-pr).

## Missing feature?

We can't wait to hear about your new ideas. Please consider the size of the feature you're proposing before taking your next steps.

**Small** - You can [submit an issue](https://github.com/progressive-insurance/kherkin/issues), or just [create a pull request](#submitting-a-pr).

**Large** - Please [detail an issue](https://github.com/progressive-insurance/kherkin/issues) so that it can be discussed. This gives us a chance to make sure we can coordinate the changes and helps ensure the easiest path forward for your changes.

## Submitting a PR

1. It is always good to double check existing PRs to avoid duplicating effort.

1. Please sign our [CLA](#signing-the-cla) before creating a PR. This is required for us to accept your changes.

1. [Fork](https://docs.github.com/en/get-started/quickstart/fork-a-repo) the repo.

1. Clone the newly forked repo under your account.
    ```console
    git clone https://github.com/your-account/kherkin.git
    ```

1. Create a new branch for your changes.
    ```console
    git checkout -b your-new-branch-name
    ```

1. Run the full test suite.

    ```console
    ./gradlew connectedAndroidTest
    ```

1. Push your branch to GitHub.
    ```console
    git push -u origin your-new-branch-name
    ```

1. In GitHub [create a pull request](https://docs.github.com/en/pull-requests/collaborating-with-pull-requests/proposing-changes-to-your-work-with-pull-requests/creating-a-pull-request-from-a-fork) to `kherkin:main`.

1. Respond to feedback provided on the PR.

## Signing the CLA

Before we can accept your contribution, we require that you sign our Contributor License Agreement (CLA). The process is automated via our pull request flow. Upon submitting your PR, our GitHub bot will determine whether or not you've previously signed our CLA. If you have not, it will provide a link for you to review. Once you have, you have two choices: 

1. Accept the agreement by directing a comment to our bot - `@progressive-open-source I accept`. Your answer will be recorded and your PR can move forward. You won't have to repeat the process for future submissions unless the agreement has been updated.
1. Reject the agreement by directing a comment to our bot - `@progressive-open-source I reject`. Your answer will be recorded and your PR will be blocked from moving forward. You can always accept the agreement later if you change your mind.
