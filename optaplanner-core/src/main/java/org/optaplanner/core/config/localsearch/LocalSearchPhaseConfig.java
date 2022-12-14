/*
 * Copyright 2020 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.optaplanner.core.config.localsearch;

import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ThreadFactory;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;

import org.optaplanner.core.config.heuristic.selector.common.SelectionCacheType;
import org.optaplanner.core.config.heuristic.selector.common.SelectionOrder;
import org.optaplanner.core.config.heuristic.selector.move.MoveSelectorConfig;
import org.optaplanner.core.config.heuristic.selector.move.composite.CartesianProductMoveSelectorConfig;
import org.optaplanner.core.config.heuristic.selector.move.composite.UnionMoveSelectorConfig;
import org.optaplanner.core.config.heuristic.selector.move.factory.MoveIteratorFactoryConfig;
import org.optaplanner.core.config.heuristic.selector.move.factory.MoveListFactoryConfig;
import org.optaplanner.core.config.heuristic.selector.move.generic.ChangeMoveSelectorConfig;
import org.optaplanner.core.config.heuristic.selector.move.generic.PillarChangeMoveSelectorConfig;
import org.optaplanner.core.config.heuristic.selector.move.generic.PillarSwapMoveSelectorConfig;
import org.optaplanner.core.config.heuristic.selector.move.generic.SwapMoveSelectorConfig;
import org.optaplanner.core.config.heuristic.selector.move.generic.chained.KOptMoveSelectorConfig;
import org.optaplanner.core.config.heuristic.selector.move.generic.chained.SubChainChangeMoveSelectorConfig;
import org.optaplanner.core.config.heuristic.selector.move.generic.chained.SubChainSwapMoveSelectorConfig;
import org.optaplanner.core.config.heuristic.selector.move.generic.chained.TailChainSwapMoveSelectorConfig;
import org.optaplanner.core.config.localsearch.decider.acceptor.AcceptorType;
import org.optaplanner.core.config.localsearch.decider.acceptor.LocalSearchAcceptorConfig;
import org.optaplanner.core.config.localsearch.decider.forager.LocalSearchForagerConfig;
import org.optaplanner.core.config.localsearch.decider.forager.LocalSearchPickEarlyType;
import org.optaplanner.core.config.phase.PhaseConfig;
import org.optaplanner.core.config.solver.EnvironmentMode;
import org.optaplanner.core.config.util.ConfigUtils;
import org.optaplanner.core.impl.heuristic.HeuristicConfigPolicy;
import org.optaplanner.core.impl.heuristic.selector.move.MoveSelector;
import org.optaplanner.core.impl.localsearch.DefaultLocalSearchPhase;
import org.optaplanner.core.impl.localsearch.LocalSearchPhase;
import org.optaplanner.core.impl.localsearch.decider.LocalSearchDecider;
import org.optaplanner.core.impl.localsearch.decider.MultiThreadedLocalSearchDecider;
import org.optaplanner.core.impl.localsearch.decider.acceptor.Acceptor;
import org.optaplanner.core.impl.localsearch.decider.forager.LocalSearchForager;
import org.optaplanner.core.impl.solver.recaller.BestSolutionRecaller;
import org.optaplanner.core.impl.solver.termination.Termination;
import org.optaplanner.core.impl.solver.thread.ChildThreadType;

public class LocalSearchPhaseConfig extends PhaseConfig<LocalSearchPhaseConfig> {

    public static final String XML_ELEMENT_NAME = "localSearch";

    // Warning: all fields are null (and not defaulted) because they can be inherited
    // and also because the input config file should match the output config file

    protected LocalSearchType localSearchType = null;

    @XmlElements({
            @XmlElement(name = CartesianProductMoveSelectorConfig.XML_ELEMENT_NAME,
                    type = CartesianProductMoveSelectorConfig.class),
            @XmlElement(name = ChangeMoveSelectorConfig.XML_ELEMENT_NAME, type = ChangeMoveSelectorConfig.class),
            @XmlElement(name = KOptMoveSelectorConfig.XML_ELEMENT_NAME, type = KOptMoveSelectorConfig.class),
            @XmlElement(name = MoveIteratorFactoryConfig.XML_ELEMENT_NAME, type = MoveIteratorFactoryConfig.class),
            @XmlElement(name = MoveListFactoryConfig.XML_ELEMENT_NAME, type = MoveListFactoryConfig.class),
            @XmlElement(name = PillarChangeMoveSelectorConfig.XML_ELEMENT_NAME,
                    type = PillarChangeMoveSelectorConfig.class),
            @XmlElement(name = PillarSwapMoveSelectorConfig.XML_ELEMENT_NAME, type = PillarSwapMoveSelectorConfig.class),
            @XmlElement(name = SubChainChangeMoveSelectorConfig.XML_ELEMENT_NAME,
                    type = SubChainChangeMoveSelectorConfig.class),
            @XmlElement(name = SubChainSwapMoveSelectorConfig.XML_ELEMENT_NAME,
                    type = SubChainSwapMoveSelectorConfig.class),
            @XmlElement(name = SwapMoveSelectorConfig.XML_ELEMENT_NAME, type = SwapMoveSelectorConfig.class),
            @XmlElement(name = TailChainSwapMoveSelectorConfig.XML_ELEMENT_NAME,
                    type = TailChainSwapMoveSelectorConfig.class),
            @XmlElement(name = UnionMoveSelectorConfig.XML_ELEMENT_NAME, type = UnionMoveSelectorConfig.class)
    })
    private MoveSelectorConfig moveSelectorConfig = null;
    @XmlElement(name = "acceptor")
    private LocalSearchAcceptorConfig acceptorConfig = null;
    @XmlElement(name = "forager")
    private LocalSearchForagerConfig foragerConfig = null;

    // ************************************************************************
    // Constructors and simple getters/setters
    // ************************************************************************

    public LocalSearchType getLocalSearchType() {
        return localSearchType;
    }

    public void setLocalSearchType(LocalSearchType localSearchType) {
        this.localSearchType = localSearchType;
    }

    public MoveSelectorConfig getMoveSelectorConfig() {
        return moveSelectorConfig;
    }

    public void setMoveSelectorConfig(MoveSelectorConfig moveSelectorConfig) {
        this.moveSelectorConfig = moveSelectorConfig;
    }

    public LocalSearchAcceptorConfig getAcceptorConfig() {
        return acceptorConfig;
    }

    public void setAcceptorConfig(LocalSearchAcceptorConfig acceptorConfig) {
        this.acceptorConfig = acceptorConfig;
    }

    public LocalSearchForagerConfig getForagerConfig() {
        return foragerConfig;
    }

    public void setForagerConfig(LocalSearchForagerConfig foragerConfig) {
        this.foragerConfig = foragerConfig;
    }

    // ************************************************************************
    // With methods
    // ************************************************************************

    public LocalSearchPhaseConfig withLocalSearchType(LocalSearchType localSearchType) {
        this.localSearchType = localSearchType;
        return this;
    }

    public LocalSearchPhaseConfig withMoveSelectorConfig(MoveSelectorConfig moveSelectorConfig) {
        this.moveSelectorConfig = moveSelectorConfig;
        return this;
    }

    public LocalSearchPhaseConfig withAcceptorConfig(LocalSearchAcceptorConfig acceptorConfig) {
        this.acceptorConfig = acceptorConfig;
        return this;
    }

    public LocalSearchPhaseConfig withForagerConfig(LocalSearchForagerConfig foragerConfig) {
        this.foragerConfig = foragerConfig;
        return this;
    }

    // ************************************************************************
    // Builder methods
    // ************************************************************************

    @Override
    public LocalSearchPhase buildPhase(int phaseIndex, HeuristicConfigPolicy solverConfigPolicy,
            BestSolutionRecaller bestSolutionRecaller, Termination solverTermination) {
        HeuristicConfigPolicy phaseConfigPolicy = solverConfigPolicy.createPhaseConfigPolicy();
        DefaultLocalSearchPhase phase = new DefaultLocalSearchPhase(
                phaseIndex, solverConfigPolicy.getLogIndentation(), bestSolutionRecaller,
                buildPhaseTermination(phaseConfigPolicy, solverTermination));
        phase.setDecider(buildDecider(phaseConfigPolicy,
                phase.getTermination()));
        EnvironmentMode environmentMode = phaseConfigPolicy.getEnvironmentMode();
        if (environmentMode.isNonIntrusiveFullAsserted()) {
            phase.setAssertStepScoreFromScratch(true);
        }
        if (environmentMode.isIntrusiveFastAsserted()) {
            phase.setAssertExpectedStepScore(true);
            phase.setAssertShadowVariablesAreNotStaleAfterStep(true);
        }
        return phase;
    }

    private LocalSearchDecider buildDecider(HeuristicConfigPolicy configPolicy, Termination termination) {
        MoveSelector moveSelector = buildMoveSelector(configPolicy);
        Acceptor acceptor = buildAcceptor(configPolicy);
        LocalSearchForager forager = buildForager(configPolicy);
        if (moveSelector.isNeverEnding() && !forager.supportsNeverEndingMoveSelector()) {
            throw new IllegalStateException("The moveSelector (" + moveSelector
                    + ") has neverEnding (" + moveSelector.isNeverEnding()
                    + "), but the forager (" + forager
                    + ") does not support it.\n"
                    + "Maybe configure the <forager> with an <acceptedCountLimit>.");
        }
        Integer moveThreadCount = configPolicy.getMoveThreadCount();
        EnvironmentMode environmentMode = configPolicy.getEnvironmentMode();
        LocalSearchDecider decider;
        if (moveThreadCount == null) {
            decider = new LocalSearchDecider(configPolicy.getLogIndentation(),
                    termination, moveSelector, acceptor, forager);
        } else {
            Integer moveThreadBufferSize = configPolicy.getMoveThreadBufferSize();
            if (moveThreadBufferSize == null) {
                // TODO Verify this is a good default by more meticulous benchmarking on multiple machines and JDK's
                // If it's too low, move threads will need to wait on the buffer, which hurts performance
                // If it's too high, more moves are selected that aren't foraged
                moveThreadBufferSize = 10;
            }
            ThreadFactory threadFactory = configPolicy.buildThreadFactory(ChildThreadType.MOVE_THREAD);
            int selectedMoveBufferSize = moveThreadCount * moveThreadBufferSize;
            MultiThreadedLocalSearchDecider multiThreadedDecider = new MultiThreadedLocalSearchDecider(
                    configPolicy.getLogIndentation(), termination, moveSelector, acceptor, forager,
                    threadFactory, moveThreadCount, selectedMoveBufferSize);
            if (environmentMode.isNonIntrusiveFullAsserted()) {
                multiThreadedDecider.setAssertStepScoreFromScratch(true);
            }
            if (environmentMode.isIntrusiveFastAsserted()) {
                multiThreadedDecider.setAssertExpectedStepScore(true);
                multiThreadedDecider.setAssertShadowVariablesAreNotStaleAfterStep(true);
            }
            decider = multiThreadedDecider;
        }
        if (environmentMode.isNonIntrusiveFullAsserted()) {
            decider.setAssertMoveScoreFromScratch(true);
        }
        if (environmentMode.isIntrusiveFastAsserted()) {
            decider.setAssertExpectedUndoMoveScore(true);
        }
        return decider;
    }

    protected Acceptor buildAcceptor(HeuristicConfigPolicy configPolicy) {
        LocalSearchAcceptorConfig acceptorConfig_;
        if (acceptorConfig != null) {
            if (localSearchType != null) {
                throw new IllegalArgumentException("The localSearchType (" + localSearchType
                        + ") must not be configured if the acceptorConfig (" + acceptorConfig
                        + ") is explicitly configured.");
            }
            acceptorConfig_ = acceptorConfig;
        } else {
            LocalSearchType localSearchType_ = defaultIfNull(localSearchType, LocalSearchType.LATE_ACCEPTANCE);
            acceptorConfig_ = new LocalSearchAcceptorConfig();
            switch (localSearchType_) {
                case HILL_CLIMBING:
                case VARIABLE_NEIGHBORHOOD_DESCENT:
                    acceptorConfig_.setAcceptorTypeList(Collections.singletonList(AcceptorType.HILL_CLIMBING));
                    break;
                case TABU_SEARCH:
                    acceptorConfig_.setAcceptorTypeList(Collections.singletonList(AcceptorType.ENTITY_TABU));
                    break;
                case SIMULATED_ANNEALING:
                    acceptorConfig_.setAcceptorTypeList(Collections.singletonList(AcceptorType.SIMULATED_ANNEALING));
                    break;
                case LATE_ACCEPTANCE:
                    acceptorConfig_.setAcceptorTypeList(Collections.singletonList(AcceptorType.LATE_ACCEPTANCE));
                    break;
                case GREAT_DELUGE:
                    acceptorConfig_.setAcceptorTypeList(Collections.singletonList(AcceptorType.GREAT_DELUGE));
                    break;
                default:
                    throw new IllegalStateException("The localSearchType (" + localSearchType_
                            + ") is not implemented.");
            }
        }
        return acceptorConfig_.buildAcceptor(configPolicy);
    }

    protected LocalSearchForager buildForager(HeuristicConfigPolicy configPolicy) {
        LocalSearchForagerConfig foragerConfig_;
        if (foragerConfig != null) {
            if (localSearchType != null) {
                throw new IllegalArgumentException("The localSearchType (" + localSearchType
                        + ") must not be configured if the foragerConfig (" + foragerConfig
                        + ") is explicitly configured.");
            }
            foragerConfig_ = foragerConfig;
        } else {
            LocalSearchType localSearchType_ = defaultIfNull(localSearchType, LocalSearchType.LATE_ACCEPTANCE);
            foragerConfig_ = new LocalSearchForagerConfig();
            switch (localSearchType_) {
                case HILL_CLIMBING:
                    foragerConfig_.setAcceptedCountLimit(1);
                    break;
                case TABU_SEARCH:
                    // Slow stepping algorithm
                    foragerConfig_.setAcceptedCountLimit(1000);
                    break;
                case SIMULATED_ANNEALING:
                case LATE_ACCEPTANCE:
                case GREAT_DELUGE:
                    // Fast stepping algorithm
                    foragerConfig_.setAcceptedCountLimit(1);
                    break;
                case VARIABLE_NEIGHBORHOOD_DESCENT:
                    foragerConfig_.setPickEarlyType(LocalSearchPickEarlyType.FIRST_LAST_STEP_SCORE_IMPROVING);
                    break;
                default:
                    throw new IllegalStateException("The localSearchType (" + localSearchType_
                            + ") is not implemented.");
            }
        }
        return foragerConfig_.buildForager(configPolicy);
    }

    protected MoveSelector buildMoveSelector(HeuristicConfigPolicy configPolicy) {
        MoveSelector moveSelector;
        SelectionCacheType defaultCacheType = SelectionCacheType.JUST_IN_TIME;
        SelectionOrder defaultSelectionOrder;
        if (localSearchType == LocalSearchType.VARIABLE_NEIGHBORHOOD_DESCENT) {
            defaultSelectionOrder = SelectionOrder.ORIGINAL;
        } else {
            defaultSelectionOrder = SelectionOrder.RANDOM;
        }
        if (moveSelectorConfig == null) {
            // Default to changeMoveSelector and swapMoveSelector
            UnionMoveSelectorConfig unionMoveSelectorConfig = new UnionMoveSelectorConfig();
            unionMoveSelectorConfig.setMoveSelectorConfigList(Arrays.asList(
                    new ChangeMoveSelectorConfig(), new SwapMoveSelectorConfig()));
            moveSelector = unionMoveSelectorConfig.buildMoveSelector(configPolicy,
                    defaultCacheType, defaultSelectionOrder);
        } else {
            moveSelector = moveSelectorConfig.buildMoveSelector(configPolicy, defaultCacheType, defaultSelectionOrder);
        }
        return moveSelector;
    }

    @Override
    public LocalSearchPhaseConfig inherit(LocalSearchPhaseConfig inheritedConfig) {
        super.inherit(inheritedConfig);
        localSearchType = ConfigUtils.inheritOverwritableProperty(localSearchType,
                inheritedConfig.getLocalSearchType());
        setMoveSelectorConfig(ConfigUtils.inheritOverwritableProperty(
                getMoveSelectorConfig(), inheritedConfig.getMoveSelectorConfig()));
        acceptorConfig = ConfigUtils.inheritConfig(acceptorConfig, inheritedConfig.getAcceptorConfig());
        foragerConfig = ConfigUtils.inheritConfig(foragerConfig, inheritedConfig.getForagerConfig());
        return this;
    }

    @Override
    public LocalSearchPhaseConfig copyConfig() {
        return new LocalSearchPhaseConfig().inherit(this);
    }

}
