# Rate Limiter: Token Bucket (Practical Basics)

## Problem
Protect APIs from bursts and abuse while still allowing short spikes in traffic for legitimate users.

## Key components
- **Bucket per identity**: user ID, API key, IP, or tenant.
- **Refill policy**: add tokens at a fixed rate (for example, 50 tokens/second).
- **Capacity**: max tokens in the bucket controls burst size.
- **Admission check**: request is allowed if at least one token exists; otherwise rejected (HTTP 429) or delayed.
- **Storage**:
  - Single node: in-memory counter.
  - Distributed: Redis with Lua script for atomic check-and-decrement.

## Trade-offs
- **Pros**: smooths traffic, supports bursts, simple mental model.
- **Cons**: distributed consistency and clock skew can cause edge-case drift.
- **Design choice**: local limiter is fast but per-instance; centralized Redis is consistent but adds network latency.

## Real-world tools
- **Kubernetes ingress**: NGINX rate-limit annotations.
- **API gateways**: Kong, Envoy, AWS API Gateway usage plans.
- **Datastore**: Redis for shared counters + Lua atomicity.
